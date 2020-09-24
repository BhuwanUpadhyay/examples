package example.interfaces.events;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderItem(@JsonProperty("itemId") String itemId,
                        @JsonProperty("quantity") Integer quantity) {
}
