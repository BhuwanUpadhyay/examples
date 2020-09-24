package example.application.commandservices;

import example.domain.ItemOrder;
import example.infrastructure.jpa.ItemOrderRepository;
import example.interfaces.events.OrderBilled;
import example.interfaces.events.OrderShipped;
import org.springframework.stereotype.Service;

@Service
public class ShipmentCommandService {

  private final ItemOrderRepository itemOrderRepository;

  public ShipmentCommandService(
      ItemOrderRepository itemOrderRepository) {
    this.itemOrderRepository = itemOrderRepository;
  }

  public void execute(OrderShipped command) {
    ItemOrder itemOrder = itemOrderRepository.findByOrderId(command.orderId());
    itemOrder.shipItems(command.address());
    this.itemOrderRepository.save(itemOrder);
  }
}
