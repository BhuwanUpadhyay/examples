package example.infrastructure.brokers;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface OrderEventSource {

  String ORDERS = "orders";

  @Input(ORDERS)
  SubscribableChannel orders();
}
