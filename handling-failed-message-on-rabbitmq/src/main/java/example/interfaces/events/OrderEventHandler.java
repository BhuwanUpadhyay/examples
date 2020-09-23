package example.interfaces.events;

import example.application.commandservices.CreateOrderCommandService;
import example.infrastructure.brokers.OrderEventSource;
import java.util.Map;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class OrderEventHandler {

  private final CreateOrderCommandService createOrderCommandService;

  public OrderEventHandler(
      CreateOrderCommandService createOrderCommandService) {
    this.createOrderCommandService = createOrderCommandService;
  }

  @StreamListener(target = OrderEventSource.CHANNEL)
  public void receiveEvent(@Payload OrderCreated command, @Headers Map<String, Object> headers) {
    createOrderCommandService.execute(command);
  }
}
