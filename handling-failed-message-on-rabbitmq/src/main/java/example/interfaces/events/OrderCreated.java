package example.interfaces.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record OrderCreatedEvent(
    @JsonProperty("orderId") String orderId,
    @JsonProperty("customerId") String customerId,
    @JsonProperty("items") List<OrderItem> items) {
}
