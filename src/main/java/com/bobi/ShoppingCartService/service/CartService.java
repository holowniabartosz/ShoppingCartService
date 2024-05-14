package com.bobi.ShoppingCartService.service;

import com.bobi.ShoppingCartService.model.shoppingcart.ShoppingCartDTO;

import java.util.List;

public interface CartService {

    ShoppingCartDTO addProductToCart(String cartId, String product) throws Exception;

    ShoppingCartDTO createEmptyCart() throws Exception;

    ShoppingCartDTO removeProductFromCart(String cartId, String productToRemove) throws Exception;

    List<ShoppingCartDTO> getAllCarts() throws Exception;

    List<String> getCartContents(String cartId) throws Exception;
}