package example.domain.commands;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderBilled(
    @JsonProperty("orderId") String orderId,
    @JsonProperty("paidAmount") String paidAmount) {
}
