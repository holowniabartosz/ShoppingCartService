package com.bobi.ShoppingCartService.model.shopping_cart;

import com.bobi.ShoppingCartService.model.product.CartProduct;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class ShoppingCart {
    private final String prefix = "cart:";
    private final String cartId;
    private final Instant creationTime;
    private final List<CartProduct> contents;

    public ShoppingCart() {
        this.cartId = prefix + UUID.randomUUID().toString();
        this.creationTime = Instant.now();
        this.contents = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ShoppingCart))
            return false;

        ShoppingCart other = (ShoppingCart) o;

        return cartId != null &&
                cartId.equals(other.getCartId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
