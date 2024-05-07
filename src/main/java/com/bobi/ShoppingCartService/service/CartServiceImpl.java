package com.bobi.ShoppingCartService.service;

import com.bobi.ShoppingCartService.model.product.CartProduct;
import com.bobi.ShoppingCartService.model.shopping_cart.ShoppingCart;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService{
    @Override
    public ShoppingCart addProductToCart(String cartId, CartProduct product) {
        return null;
    }
}
