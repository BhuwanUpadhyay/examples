package example.infrastructure.brokers;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface OrderEventSource {

  String OUT_CHANNEL = "outEvents";
  String IN_CHANNEL = "inEvents";

  @Output(OUT_CHANNEL)
  MessageChannel outEvents();

  @Input(IN_CHANNEL)
  SubscribableChannel inEvents();
}
