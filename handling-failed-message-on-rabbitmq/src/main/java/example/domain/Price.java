package example.domain;

import java.math.BigDecimal;
import javax.persistence.Embeddable;

@Embeddable
public class Price {

  private BigDecimal price;
  private String currency;

  public Price() {
  }

  public Price(BigDecimal price, String currency) {
    this.price = price;
    this.currency = currency;
  }
}
