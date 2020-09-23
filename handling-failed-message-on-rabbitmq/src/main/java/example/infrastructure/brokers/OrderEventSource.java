package example.infrastructure.brokers;

public interface CartEventSource {

    String CHANNEL = "osEvents";

    @Output(CHANNEL)
    MessageChannel eventsOut();

    @Input(CHANNEL)
    SubscribableChannel eventsIn();
}
