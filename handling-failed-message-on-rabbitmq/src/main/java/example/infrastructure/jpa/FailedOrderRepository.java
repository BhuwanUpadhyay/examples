package example.infrastructure.jpa;

import example.domain.model.FailedOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailedOrderRepository extends JpaRepository<FailedOrder, Long> {
}
