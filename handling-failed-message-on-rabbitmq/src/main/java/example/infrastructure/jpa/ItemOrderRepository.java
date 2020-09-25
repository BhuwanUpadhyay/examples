package example.infrastructure.jpa;

import example.domain.model.ItemOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemOrderRepository extends JpaRepository<ItemOrder, Long> {

  ItemOrder findByOrderId(String orderId);
}
