package com.bobi.ShoppingCartService.model.shoppingcart;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Getter
@Setter
public class ShoppingCart {
    private final String cartId;
    private final Instant creationTime;
    private List<String> contentsList;

    public ShoppingCart() {
        this.cartId = "cart:" + UUID.randomUUID();
        this.creationTime = Instant.now();
        this.contentsList = new ArrayList<>();
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
