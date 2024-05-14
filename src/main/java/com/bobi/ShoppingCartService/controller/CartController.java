package com.bobi.ShoppingCartService.controller;

import com.bobi.ShoppingCartService.model.shoppingcart.ShoppingCartDTO;
import com.bobi.ShoppingCartService.remote.ProductsServiceClient;
import com.bobi.ShoppingCartService.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private final CartService cartService;
    @Autowired
    private final ProductsServiceClient productsServiceClient;

    @GetMapping
    public List<ShoppingCartDTO> getAllCarts() throws Exception {
        return cartService.getAllCarts();
    }

    @GetMapping("/{cartId}")
    public List<String> getCartContents(@PathVariable String cartId) throws Exception {
        return cartService.getCartContents(cartId);
    }

    @PostMapping("/{name}")
    public ShoppingCartDTO addProductToCart(@RequestParam("cartId") String cartId, @PathVariable String name,
                                         @RequestBody List<Integer> configuration) throws Exception {
        String configuredProduct = productsServiceClient.addProductAndConfigureIfApplicable(name, configuration);
        return cartService.addProductToCart(cartId, configuredProduct);
    }

    @DeleteMapping
    public ShoppingCartDTO removeProductFromCart(@RequestParam String cartId, @RequestBody String productToRemove) throws Exception {
        return cartService.removeProductFromCart(cartId, productToRemove);
    }

    @PostMapping
    public ShoppingCartDTO createEmptyCart() throws Exception {
        return cartService.createEmptyCart();
    }
}