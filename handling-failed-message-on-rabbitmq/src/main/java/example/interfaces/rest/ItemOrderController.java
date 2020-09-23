package example.interfaces.rest;

import example.domain.ItemOrder;
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

  public ItemOrderController(ItemOrderRepository itemOrderRepository) {
    this.itemOrderRepository = itemOrderRepository;
  }

  @GetMapping
  public List<ItemOrder> getItemOrders() {
    return itemOrderRepository.findAll();
  }

  @GetMapping("/{orderId}")
  public ItemOrder getItemOrder(
      @NotBlank(message = "orderId.empty")
      @PathVariable("orderId") String orderId) {
    return itemOrderRepository.findByOrderId(orderId);
  }
}
