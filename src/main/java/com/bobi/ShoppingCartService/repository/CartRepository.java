package com.bobi.ShoppingCartService.repository;

import com.bobi.ShoppingCartService.model.product.CartProduct;
import com.bobi.ShoppingCartService.model.shopping_cart.ShoppingCart;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class CartRepository {

    private final StringRedisTemplate template;
    private final ObjectMapper objectMapper;

    public CartRepository(StringRedisTemplate template) {
        this.template = template;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); // to handle Instant properly
    }

    public ShoppingCart createEmptyCart() throws Exception {
        ShoppingCart cart = new ShoppingCart();
        saveCart(cart);
        return cart;
    }

    private void saveCart(ShoppingCart cart) throws Exception {
        String jsonCart = objectMapper.writeValueAsString(cart);
        template.opsForValue().set(cart.getCartId(), jsonCart, Duration.ofMinutes(90));
    }

    private ShoppingCart loadCart(String cartId) throws Exception {
        String jsonCart = template.opsForValue().get(cartId);
        return jsonCart == null ? null : objectMapper.readValue(jsonCart, ShoppingCart.class);
    }

    public ShoppingCart addProductToCart(String cartId, CartProduct product) throws Exception {
        ShoppingCart cart = loadCart(cartId);
        cart.getContents().add(product);
        saveCart(cart);
        return cart;
    }

    public ShoppingCart removeProductFromCart(String cartId, CartProduct productToRemove) throws Exception {
        ShoppingCart cart = loadCart(cartId);
        cart.getContents().removeIf(product -> product.equals(productToRemove));
        saveCart(cart);
        return cart;
    }

    public List<ShoppingCart> getAllCarts() throws Exception {
        Set<String> cartKeys = template.keys("cart:*");
        List<ShoppingCart> carts = new ArrayList<>();
        ValueOperations<String, String> ops = template.opsForValue();
        if (cartKeys != null) {
            for (String key : cartKeys) {
                String cartData = ops.get(key);
                ShoppingCart cart = objectMapper.readValue(cartData, ShoppingCart.class);
                carts.add(cart);
            }
        }
        return carts;
    }
}
