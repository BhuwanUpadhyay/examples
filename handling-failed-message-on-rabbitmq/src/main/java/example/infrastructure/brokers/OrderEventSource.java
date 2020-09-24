package example.infrastructure.brokers;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface OrderEventSource {

  String ORDERS = "orders";
  String PAYMENTS = "payments";
  String FAILURES = "failures";

  @Output(FAILURES)
  MessageChannel failures();
  
  @Input(ORDERS)
  SubscribableChannel orders();

  @Input(PAYMENTS)
  SubscribableChannel payments();
}
