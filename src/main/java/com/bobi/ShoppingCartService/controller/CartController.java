package com.bobi.ShoppingCartService.controller;

import com.bobi.ShoppingCartService.model.product.CartProduct;
import com.bobi.ShoppingCartService.model.shopping_cart.ShoppingCart;
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
    public List<ShoppingCart> getAllCarts() throws Exception {
        return cartService.getAllCarts();
    }

    @PostMapping("/{name}")
    public ShoppingCart addProductToCart(@RequestParam("cartId") String cartId, @PathVariable String name,
                                         @RequestBody List<Integer> configuration) throws Exception {
        String configuredProduct = productsServiceClient.addProductAndConfigureIfApplicable(name, configuration);
        return cartService.addProductToCart(cartId, configuredProduct);
    }

    @DeleteMapping
    public ShoppingCart removeProductFromCart(@RequestParam String cartId, @RequestBody CartProduct productToRemove) throws Exception {
        return cartService.removeProductFromCart(cartId, productToRemove);
    }

    @PostMapping
    public ShoppingCart createEmptyCart() throws Exception {
        return cartService.createEmptyCart();
    }

    @GetMapping("/test-view")
    public CartProduct getTestView(@RequestParam("cartId") String cartId,
                                   @RequestParam("cartProductId") String cartProductId) throws Exception {
        return cartService.getTestView(cartId, cartProductId);
    }
}