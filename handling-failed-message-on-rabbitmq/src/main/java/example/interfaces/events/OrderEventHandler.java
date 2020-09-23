package example.interfaces.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
  private final ObjectMapper objectMapper;

  public OrderEventHandler(
      CreateOrderCommandService createOrderCommandService,
      ObjectMapper objectMapper) {
    this.createOrderCommandService = createOrderCommandService;
    this.objectMapper = objectMapper;
  }

  @StreamListener(target = OrderEventSource.ORDERS)
  public void receiveEvent(@Payload String payload, @Headers Map<String, Object> headers) {
    OrderCreated command;
    try {
      command = this.objectMapper.readValue(payload, OrderCreated.class);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
    createOrderCommandService.execute(command);
  }
}
