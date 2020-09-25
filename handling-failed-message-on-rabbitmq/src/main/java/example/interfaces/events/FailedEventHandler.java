package example.interfaces.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import example.infrastructure.brokers.OrderEventSource;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class FailedEventHandler {

  private final Logger log = LoggerFactory.getLogger(FailedEventHandler.class);

  @StreamListener(target = OrderEventSource.ORDER_FAILED)
  public void receiveFailures(@Payload FailedEvent command, @Headers Map<String, Object> headers) {
    log.info("Received failures: {}", command);
  }
}
