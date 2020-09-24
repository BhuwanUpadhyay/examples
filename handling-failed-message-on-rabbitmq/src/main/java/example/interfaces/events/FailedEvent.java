package example.interfaces.events;

public record FailedEvent<T> (T payload, String reason) {
}
