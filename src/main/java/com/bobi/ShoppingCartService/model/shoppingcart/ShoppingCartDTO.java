package com.bobi.ShoppingCartService.model.shoppingcart;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class ShoppingCartDTO {
    private final String cartId;
    private final Instant creationTime;
    private List<String> contentsList;

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
