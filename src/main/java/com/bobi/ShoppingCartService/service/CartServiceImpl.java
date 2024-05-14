package com.bobi.ShoppingCartService.service;

import com.bobi.ShoppingCartService.model.mapper.ShoppingCartMapper;
import com.bobi.ShoppingCartService.model.shoppingcart.ShoppingCartDTO;
import com.bobi.ShoppingCartService.repository.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ShoppingCartMapper shoppingCartMapper;

    @Override
    public ShoppingCartDTO addProductToCart(String cartId, String product) throws Exception {
        if (product == null){
            throw new NullPointerException();
        }
        return shoppingCartMapper.toDTO(cartRepository.addProductToCart(cartId, product));
    }

    @Override
    public ShoppingCartDTO createEmptyCart() throws Exception {
        return shoppingCartMapper.toDTO(cartRepository.createEmptyCart());
    }

    @Override
    public ShoppingCartDTO removeProductFromCart(String cartId, String productToRemove) throws Exception {
        return shoppingCartMapper.toDTO(cartRepository.removeProductFromCart(cartId, productToRemove));
    }

    @Override
    public List<ShoppingCartDTO> getAllCarts() throws Exception {
        return cartRepository.getAllCarts();
    }

    @Override
    public List<String> getCartContents(String cartId) throws Exception {
        return cartRepository.loadCart(cartId).getContentsList();
    }
}
