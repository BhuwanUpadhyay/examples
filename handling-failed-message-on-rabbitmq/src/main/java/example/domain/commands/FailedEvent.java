package example.domain.commands;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

public record FailedEvent(@JsonProperty("payload") String payload,
                          @JsonProperty("reason") String reason,
                          @JsonProperty("id") UUID id,
                          @JsonProperty("timestamp") Long timestamp,
                          @JsonProperty("exchange") String exchange,
                          @JsonProperty("queue") String queue) {
}
