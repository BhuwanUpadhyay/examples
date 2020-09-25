package example.domain.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record OrderCreated(
    @JsonProperty("orderId") String orderId,
    @JsonProperty("customerId") String customerId,
    @JsonProperty("items") List<OrderItem> items) {
}
