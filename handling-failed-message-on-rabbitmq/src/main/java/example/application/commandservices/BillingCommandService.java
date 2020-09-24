package example.application.commandservices;

import example.domain.ItemOrder;
import example.infrastructure.jpa.ItemOrderRepository;
import example.interfaces.events.OrderBilled;
import org.springframework.stereotype.Service;

@Service
public class ShipmentCommandService {

  private final ItemOrderRepository itemOrderRepository;

  public ShipmentCommandService(
      ItemOrderRepository itemOrderRepository) {
    this.itemOrderRepository = itemOrderRepository;
  }

  public void execute(OrderBilled command) {
    ItemOrder itemOrder = itemOrderRepository.findByOrderId(command.orderId());
    itemOrder.shipItems();
    this.itemOrderRepository.save(itemOrder);
  }
}
