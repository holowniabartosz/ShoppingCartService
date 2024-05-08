package com.bobi.ShoppingCartService.model.product;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Data
@NoArgsConstructor
public class CartProduct {
    private static String PREFIX = "product:";
    private final String cartProductId = PREFIX + UUID.randomUUID();
    private String productInJson;

    public CartProduct(String productInJson) {
        this.productInJson = productInJson;
    }
}
