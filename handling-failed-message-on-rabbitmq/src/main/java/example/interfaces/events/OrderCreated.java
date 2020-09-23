package example.interfaces.events;

import java.util.List;

public record OrderCreated(String orderId, String customerId, List<OrderItem> items) {
}
