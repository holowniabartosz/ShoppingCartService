package com.bobi.ShoppingCartService.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(
        name = "products-service-client",
        url = "${products-service.url}",
        configuration = FeignConfig.class
)
public interface ProductsServiceClient {
    @PostMapping("/product/{name}/configure")
    String addProductAndConfigureIfApplicable(@PathVariable String name, @RequestBody List<Integer> configuration);
}
