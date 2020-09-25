package example.application.commandservices;

import example.application.outboundservices.ExternalCatalogItemService;
import example.domain.model.ItemOrder;
import example.domain.model.OrderLine;
import example.domain.model.Price;
import example.infrastructure.jpa.ItemOrderRepository;
import example.domain.commands.OrderCreated;
import example.domain.commands.OrderItem;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CreateOrderCommandService {

  private final ItemOrderRepository itemOrderRepository;
  private final ExternalCatalogItemService externalCatalogItemService;

  public CreateOrderCommandService(
      ItemOrderRepository itemOrderRepository,
      ExternalCatalogItemService externalCatalogItemService) {
    this.itemOrderRepository = itemOrderRepository;
    this.externalCatalogItemService = externalCatalogItemService;
  }

  public void execute(OrderCreated command) {
    ItemOrder itemOrder = new ItemOrder(command.orderId(), command.customerId());
    List<OrderLine> orderLines =
        command.items().stream().map(this::createOrderLine).collect(Collectors.toList());
    itemOrder.addOrderLines(orderLines);
    this.itemOrderRepository.save(itemOrder);
  }

  private OrderLine createOrderLine(OrderItem orderItem) {
    Price price = externalCatalogItemService.getItemPrice(orderItem.itemId());
    return new OrderLine(orderItem.itemId(), orderItem.quantity(), price);
  }
}
