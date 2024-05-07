package com.bobi.ShoppingCartService.controller;

import com.bobi.ShoppingCartService.model.product.CartProduct;
import com.bobi.ShoppingCartService.model.shopping_cart.ShoppingCart;
import com.bobi.ShoppingCartService.remote.ProductsServiceClient;
import com.bobi.ShoppingCartService.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private CartService cartService;
    private ProductsServiceClient productsServiceClient;

    @PostMapping("/{name}")
    public ShoppingCart addProductToCart(@RequestParam String cartId, @PathVariable String name,
                                         @RequestBody List<String> configuration) {
        CartProduct product = productsServiceClient.getProduct(name);
        CartProduct configuredProduct = productsServiceClient.addProductAndConfigureIfApplicable(name, configuration);
        return cartService.addProductToCart(cartId, configuredProduct);
    }
}