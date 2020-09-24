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

  private final ObjectMapper objectMapper;
  private final Logger log = LoggerFactory.getLogger(FailedEventHandler.class);

  public FailedEventHandler(ObjectMapper objectMapper) {
    this.objectMapper = objectMapper;
  }

  @StreamListener(target = OrderEventSource.ORDER_FAILED)
  public void receiveFailures(@Payload String payload, @Headers Map<String, Object> headers) {
    log.info("Received failures: {}", payload);
    FailedEvent command;
    try {
      command = this.objectMapper.readValue(payload, FailedEvent.class);
    } catch (JsonProcessingException e) {
      log.debug("Unable to parse!", e);
    }
  }
}
