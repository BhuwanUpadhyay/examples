package example.interfaces.events;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderShipped(
    @JsonProperty("orderId") String orderId,
    @JsonProperty("address") String address) {
}
