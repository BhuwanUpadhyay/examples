package example.application.commandservices;

import example.domain.model.ItemOrder;
import example.infrastructure.jpa.ItemOrderRepository;
import example.domain.commands.OrderBilled;
import org.springframework.stereotype.Service;

@Service
public class BillingCommandService {

  private final ItemOrderRepository itemOrderRepository;

  public BillingCommandService(
      ItemOrderRepository itemOrderRepository) {
    this.itemOrderRepository = itemOrderRepository;
  }

  public void execute(OrderBilled command) {
    ItemOrder itemOrder = itemOrderRepository.findByOrderId(command.orderId());
    itemOrder.billed(command.paidAmount());
    this.itemOrderRepository.save(itemOrder);
  }
}
