package com.bobi.ShoppingCartService.model.shopping_cart;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@RequiredArgsConstructor
public class ShoppingCartDTO {
    private static final String PREFIX = "cart:";
    private final String cartId;
    private final Instant creationTime;
    private final List<String> contentsList;

    public ShoppingCartDTO() {
        this.cartId = PREFIX + UUID.randomUUID();
        this.creationTime = Instant.now();
        this.contentsList = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ShoppingCartDTO))
            return false;

        ShoppingCartDTO other = (ShoppingCartDTO) o;

        return cartId != null &&
                cartId.equals(other.getCartId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
