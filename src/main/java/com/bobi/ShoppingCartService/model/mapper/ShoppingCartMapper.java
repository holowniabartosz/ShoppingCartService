package com.bobi.ShoppingCartService.model.mapper;

import com.bobi.ShoppingCartService.model.shoppingcart.ShoppingCart;
import com.bobi.ShoppingCartService.model.shoppingcart.ShoppingCartDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ShoppingCartMapper {

    ShoppingCartDTO toDTO(ShoppingCart shoppingCart);

    ShoppingCart toShoppingCart(ShoppingCartDTO shoppingCartDTO);
}