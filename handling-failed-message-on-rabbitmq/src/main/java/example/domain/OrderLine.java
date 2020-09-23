package example.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class OrderLine implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String itemId;

  private Integer quantity;

  private Price price;

  public OrderLine() {
  }

  public OrderLine(String itemId, Integer quantity, Price price) {
    this.itemId = itemId;
    this.quantity = quantity;
    this.price = price;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private ItemOrder itemOrder;

  public void setItemOrder(ItemOrder itemOrder) {
    this.itemOrder = itemOrder;
  }
}
