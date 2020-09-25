package example.domain.commands;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderItem(@JsonProperty("itemId") String itemId,
                        @JsonProperty("quantity") Integer quantity) {
}
