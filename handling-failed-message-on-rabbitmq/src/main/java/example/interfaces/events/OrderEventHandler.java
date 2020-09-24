package example.interfaces.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.application.commandservices.CreateOrderCommandService;
import example.infrastructure.brokers.OrderEventSource;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.support.ErrorMessage;
import org.springframework.stereotype.Service;

@Service
public class OrderEventHandler {

  private final CreateOrderCommandService createOrderCommandService;
  private final ObjectMapper objectMapper;
  private final OrderEventSource eventSource;

  private final Logger log = LoggerFactory.getLogger(OrderEventHandler.class);

  public OrderEventHandler(
      CreateOrderCommandService createOrderCommandService,
      ObjectMapper objectMapper, OrderEventSource eventSource) {
    this.createOrderCommandService = createOrderCommandService;
    this.objectMapper = objectMapper;
    this.eventSource = eventSource;
  }

  @StreamListener(target = OrderEventSource.ORDERS)
  public void receiveOrders(@Payload String payload, @Headers Map<String, Object> headers) {
    OrderCreated command;
    try {
      command = this.objectMapper.readValue(payload, OrderCreated.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    createOrderCommandService.execute(command);
  }
  
  @StreamListener(target = OrderEventSource.PAYMENTS)
  public void receivePayments(@Payload String payload, @Headers Map<String, Object> headers) {
    OrderCreated command;
    try {
      command = this.objectMapper.readValue(payload, OrderCreated.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    createOrderCommandService.execute(command);
  }

  //
  //@ServiceActivator(inputChannel = OrderEventSource.ORDERS)
  //public void onError(ErrorMessage errorMessage) {
  //  // Getting exception objects
  //  Throwable errorMessagePayload = errorMessage.getPayload();
  //  log.error("exception occurred", errorMessagePayload);
  //
  //  // Get message body
  //  Message<?> originalMessage = errorMessage.getOriginalMessage();
  //  if (originalMessage != null) {
  //    log.error("Message Body: {}", originalMessage.getPayload());
  //  } else {
  //    log.error("The message body is empty");
  //  }
  //}
}
