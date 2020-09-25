package example.domain.model;

import java.math.BigDecimal;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;

@Embeddable
@Access(AccessType.FIELD)
public class Price {

  private BigDecimal price;
  private String currency;

  public Price() {
  }

  public Price(BigDecimal price, String currency) {
    this.price = price;
    this.currency = currency;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public String getCurrency() {
    return currency;
  }
}
