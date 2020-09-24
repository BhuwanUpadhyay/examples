package example.application.commandservices;

import example.application.outboundservices.ExternalCatalogItemService;
import example.domain.ItemOrder;
import example.domain.OrderLine;
import example.domain.Price;
import example.infrastructure.jpa.ItemOrderRepository;
import example.interfaces.events.OrderCreated;
import example.interfaces.events.OrderItem;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class FullfillmentCommandService {

  private final ItemOrderRepository itemOrderRepository;
  private final ExternalCatalogItemService externalCatalogItemService;

  public FullfillmentCommandService(
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
