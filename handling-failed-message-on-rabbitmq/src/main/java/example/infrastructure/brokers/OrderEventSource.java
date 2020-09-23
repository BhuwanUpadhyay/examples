package example.infrastructure.brokers;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface OrderEventSource {

  String CHANNEL = "osEvents";

  @Output(CHANNEL)
  MessageChannel eventsOut();

  @Input(CHANNEL)
  SubscribableChannel eventsIn();
}
