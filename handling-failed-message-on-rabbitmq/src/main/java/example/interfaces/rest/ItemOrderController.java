package example.interfaces.rest;

import example.domain.model.FailedOrder;
import example.domain.model.ItemOrder;
import example.infrastructure.jpa.FailedOrderRepository;
import example.infrastructure.jpa.ItemOrderRepository;
import java.util.List;
import javax.validation.constraints.NotBlank;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class ItemOrderController {

  private final ItemOrderRepository itemOrderRepository;
  private final FailedOrderRepository failedOrderRepository;

  public ItemOrderController(ItemOrderRepository itemOrderRepository,
      FailedOrderRepository failedOrderRepository) {
    this.itemOrderRepository = itemOrderRepository;
    this.failedOrderRepository = failedOrderRepository;
  }

  @GetMapping
  public List<ItemOrder> getItemOrders() {
    return itemOrderRepository.findAll();
  }

  @GetMapping("/{orderId}")
  public ItemOrder getItemOrder(
      @NotBlank(message = "orderId.empty") @PathVariable("orderId") String orderId) {
    return itemOrderRepository.findByOrderId(orderId);
  }

  @GetMapping("/failed")
  public List<FailedOrder> getFailedOrders() {
    return failedOrderRepository.findAll();
  }
}
