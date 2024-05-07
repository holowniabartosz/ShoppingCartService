package com.bobi.ShoppingCartService.remote;

import com.bobi.ShoppingCartService.model.product.CartProduct;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
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
    @PostMapping(value = "/product/{name}/configure")
    CartProduct addProductAndConfigureIfApplicable(@PathVariable String name, @RequestBody List<String> configuration);

    @GetMapping(value = "/product/{name}")
    CartProduct getProduct(@PathVariable String name);
}
