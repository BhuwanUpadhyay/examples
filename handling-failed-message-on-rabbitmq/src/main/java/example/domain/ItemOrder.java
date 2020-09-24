package example.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@Access(AccessType.FIELD)
public class ItemOrder implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(unique = true)
  private String orderId;

  private String customerId;
  @SuppressWarnings("FieldMayBeFinal")
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemOrder", orphanRemoval = true)
  private List<OrderLine> orderLines = new ArrayList<>();

  @Enumerated(EnumType.STRING)
  private Status status;

  private String shippingAddress;

  public ItemOrder() {
  }

  public ItemOrder(String orderId, String customerId) {
    this.orderId = orderId;
    this.customerId = customerId;
    this.status = Status.CREATED;
  }

  public void addOrderLines(List<OrderLine> orderLines) {
    for (OrderLine orderLine : orderLines) {
      orderLine.setItemOrder(this);
    }
    this.orderLines.addAll(orderLines);
  }

  public String getOrderId() {
    return orderId;
  }

  public String getCustomerId() {
    return customerId;
  }

  public List<OrderLine> getOrderLines() {
    return orderLines;
  }

  public String getShippingAddress() {
    return shippingAddress;
  }

  public void billed(String paidAmount) {
    this.status = Status.BILLED;
  }

  public void shipItems(String shippingAddress) {
    this.shippingAddress = shippingAddress;
    this.status = Status.SHIPPED;
  }

  public enum Status {
    CREATED, BILLED, SHIPPED
  }
}
