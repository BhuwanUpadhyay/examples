package example;

import java.util.List;

public record OrderCreated(String orderId, String customerId, List<String> items) {
}
