package example.interfaces.events;

import example.application.commandservices.BillingCommandService;
import example.application.commandservices.CreateOrderCommandService;
import example.application.commandservices.ShipmentCommandService;
import example.domain.commands.FailedEvent;
import example.domain.commands.OrderBilled;
import example.domain.commands.OrderCreated;
import example.domain.commands.OrderShipped;
import example.infrastructure.brokers.OrderEventSource;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class OrderEventHandler {

  private final CreateOrderCommandService createOrderCommandService;
  private final BillingCommandService billingCommandService;
  private final ShipmentCommandService shipmentCommandService;
  private final OrderEventSource eventSource;

  private final Logger log = LoggerFactory.getLogger(OrderEventHandler.class);

  public OrderEventHandler(
      CreateOrderCommandService createOrderCommandService,
      BillingCommandService billingCommandService,
      ShipmentCommandService shipmentCommandService,
      OrderEventSource eventSource) {
    this.createOrderCommandService = createOrderCommandService;
    this.billingCommandService = billingCommandService;
    this.shipmentCommandService = shipmentCommandService;
    this.eventSource = eventSource;
  }

  @StreamListener(target = OrderEventSource.ORDER_CREATED)
  public void receiveOrders(@Payload OrderCreated command, @Headers Map<String, Object> headers) {
    createOrderCommandService.execute(command);
  }

  @StreamListener(target = OrderEventSource.ORDER_BILLED)
  public void receiveBills(@Payload OrderBilled command, @Headers Map<String, Object> headers) {
    billingCommandService.execute(command);
  }

  @StreamListener(target = OrderEventSource.ORDER_SHIPPED)
  public void receiveShipments(@Payload OrderShipped command,
      @Headers Map<String, Object> headers) {
    shipmentCommandService.execute(command);
  }

  @ServiceActivator(inputChannel = "order.created.order-service.errors")
  public void onCreatedError(ErrorMessage errorMessage) {
    this.handleErrors(errorMessage);
  }

  @ServiceActivator(inputChannel = "order.billed.order-service.errors")
  public void onBilledError(ErrorMessage errorMessage) {
    this.handleErrors(errorMessage);
  }

  @ServiceActivator(inputChannel = "order.shipped.order-service.errors")
  public void onShippedError(ErrorMessage errorMessage) {
    this.handleErrors(errorMessage);
  }

  private void handleErrors(ErrorMessage errorMessage) {
    // Getting exception objects
    Throwable throwable = errorMessage.getPayload();

    // Get message body
    Message<?> originalMessage = errorMessage.getOriginalMessage();
    if (originalMessage == null) {
      return;
    }
    MessageHeaders headers = originalMessage.getHeaders();
    String payload = new String((byte[]) originalMessage.getPayload());
    FailedEvent failedEvent = new FailedEvent(
        payload,
        throwable.getMessage(),
        headers.getId(),
        headers.getTimestamp(),
        (String) headers.get("amqp_receivedExchange"),
        (String) headers.get("amqp_consumerQueue")
    );
    log.error("Failed to process an order: {}", failedEvent);

    eventSource.failed().send(MessageBuilder.withPayload(failedEvent).build());
  }
}
