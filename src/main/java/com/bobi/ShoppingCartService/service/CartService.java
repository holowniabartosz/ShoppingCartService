package com.bobi.ShoppingCartService.service;

import com.bobi.ShoppingCartService.model.product.CartProduct;
import com.bobi.ShoppingCartService.model.shopping_cart.ShoppingCart;

import java.util.List;

public interface CartService {

    ShoppingCart addProductToCart(String cartId, String product) throws Exception;

    ShoppingCart createEmptyCart() throws Exception;

    ShoppingCart removeProductFromCart(String cartId, CartProduct productToRemove) throws Exception;

    List<ShoppingCart> getAllCarts() throws Exception;

    CartProduct getTestView(String cartId, String cartProductId) throws Exception;
}