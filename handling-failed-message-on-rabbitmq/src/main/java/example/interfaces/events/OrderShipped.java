package example.interfaces.events;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderBilled(
    @JsonProperty("orderId") String orderId,
    @JsonProperty("price") String price) {
}
