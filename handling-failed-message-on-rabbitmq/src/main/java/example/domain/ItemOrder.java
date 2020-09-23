package example.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class ItemOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String orderId;

  private String customerId;

  @SuppressWarnings("FieldMayBeFinal")
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "itemOrder", orphanRemoval = true)
  private List<OrderLine> orderLines = new ArrayList<>();
}
