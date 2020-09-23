package example.application.outboundservices;

import example.domain.Price;
import example.infrastructure.services.http.CatalogItemServiceClient;
import java.util.Optional;
import org.springframework.stereotype.Service;

import static example.infrastructure.services.http.CatalogItemServiceClient.ItemInfoResource;

@Service
public class ExternalCatalogItemService {

  private final CatalogItemServiceClient catalogItemServiceClient;

  public ExternalCatalogItemService(
      CatalogItemServiceClient catalogItemServiceClient) {
    this.catalogItemServiceClient = catalogItemServiceClient;
  }

  public Price getItemPrice(String itemId) {
    ItemInfoResource info = catalogItemServiceClient.getItemInfo(itemId);
    return Optional.ofNullable(info).map(r -> new Price(r.price(), r.currency())).orElseThrow(RuntimeException::new);
  }
}
