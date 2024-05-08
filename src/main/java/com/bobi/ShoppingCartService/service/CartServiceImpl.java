package com.bobi.ShoppingCartService.service;

import com.bobi.ShoppingCartService.model.product.CartProduct;
import com.bobi.ShoppingCartService.model.shopping_cart.ShoppingCart;
import com.bobi.ShoppingCartService.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public ShoppingCart addProductToCart(String cartId, String product) throws Exception {
        CartProduct productToSave = new CartProduct(product);
        return cartRepository.addProductToCart(cartId, productToSave);
    }

    @Override
    public ShoppingCart createEmptyCart() throws Exception {
        return cartRepository.createEmptyCart();
    }

    @Override
    public ShoppingCart removeProductFromCart(String cartId, CartProduct productToRemove) throws Exception {
        return cartRepository.removeProductFromCart(cartId, productToRemove);
    }

    @Override
    public List<ShoppingCart> getAllCarts() throws Exception {
        return cartRepository.getAllCarts();
    }

    @Override
    public CartProduct getTestView(String cartId, String cartProductId) throws Exception {
        return cartRepository.getTestView(cartId, cartProductId);
    }
}
