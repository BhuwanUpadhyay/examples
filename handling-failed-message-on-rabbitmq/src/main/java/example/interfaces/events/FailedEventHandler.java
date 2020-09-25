package example.interfaces.events;

import example.domain.model.FailedOrder;
import example.domain.commands.FailedEvent;
import example.infrastructure.brokers.OrderEventSource;
import example.infrastructure.jpa.FailedOrderRepository;
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

  private final FailedOrderRepository failedOrderRepository;

  public FailedEventHandler(FailedOrderRepository failedOrderRepository) {
    this.failedOrderRepository = failedOrderRepository;
  }

  @StreamListener(target = OrderEventSource.ORDER_FAILED)
  public void receiveFailures(@Payload FailedEvent command, @Headers Map<String, Object> headers) {
    log.info("Received failures: {}", command);
    failedOrderRepository.save(new FailedOrder(command));
  }
}
