package example.infrastructure.services.http;

import java.math.BigDecimal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "CatalogService")
public interface CatalogItemServiceClient {

  @RequestMapping(method = RequestMethod.GET, value = "/catalog-items/{itemId}")
  ItemInfoResource getItemInfo(@PathVariable("itemId") String itemId);

  record ItemInfoResource(String itemId, BigDecimal price, String currency) {

  }
}
