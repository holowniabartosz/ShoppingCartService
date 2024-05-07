package com.bobi.ShoppingCartService.service;

import com.bobi.ShoppingCartService.model.product.CartProduct;
import com.bobi.ShoppingCartService.model.shopping_cart.ShoppingCart;

public interface CartService {

    ShoppingCart addProductToCart(String cartId, CartProduct product);

}
