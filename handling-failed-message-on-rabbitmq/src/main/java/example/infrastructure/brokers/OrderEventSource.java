package example.infrastructure.brokers;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface OrderEventSource {

  String ORDER_CREATED = "orderCreated";
  String ORDER_BILLED = "orderBilled";
  String ORDER_SHIPPED = "orderShipped";
  String ORDER_FAILED = "orderFailed";

  @Input(ORDER_FAILED)
  SubscribableChannel orderFailed();

  @Output(ORDER_FAILED)
  MessageChannel failed();

  @Input(ORDER_CREATED)
  SubscribableChannel orderCreated();

  @Input(ORDER_SHIPPED)
  SubscribableChannel orderShipped();

  @Input(ORDER_BILLED)
  SubscribableChannel orderBilled();
}
