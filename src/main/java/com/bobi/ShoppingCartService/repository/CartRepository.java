package com.bobi.ShoppingCartService.repository;

import com.bobi.ShoppingCartService.model.mapper.ShoppingCartMapper;
import com.bobi.ShoppingCartService.model.shoppingcart.ShoppingCart;
import com.bobi.ShoppingCartService.model.shoppingcart.ShoppingCartDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class CartRepository {

    @Autowired
    private final StringRedisTemplate template;
    private final ObjectMapper objectMapper;
    @Autowired
    private final ShoppingCartMapper shoppingCartMapper;

    public CartRepository(StringRedisTemplate template, ShoppingCartMapper shoppingCartMapper) {
        this.template = template;
        this.shoppingCartMapper = shoppingCartMapper;
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false); // to handle Instant properly
    }

    public ShoppingCart createEmptyCart() throws Exception {
        ShoppingCart cart = new ShoppingCart();
        saveCart(cart);
        System.out.println("Cart ID: " + cart.getCartId());
        return cart;
    }

    public void saveCart(ShoppingCart cart) throws Exception {
        String jsonCart = objectMapper.writeValueAsString(cart);
        template.opsForValue().set(cart.getCartId(), jsonCart, Duration.ofMinutes(90));
    }

    public ShoppingCart loadCart(String cartId) throws Exception {
        String jsonCart = template.opsForValue().get(cartId);
        return jsonCart == null ? null : objectMapper.readValue(jsonCart, ShoppingCart.class);
    }

    public ShoppingCart addProductToCart(String cartId, String product) throws Exception {
        ShoppingCart cart = loadCart(cartId);
        cart.getContentsList().add(product);
        saveCart(cart);
        return cart;
    }

    public ShoppingCart removeProductFromCart(String cartId, String productToRemove) throws Exception {
        ShoppingCart cart = loadCart(cartId);
        cart.getContentsList().removeIf(product -> product.equals(productToRemove));
        saveCart(cart);
        return cart;
    }

    public List<ShoppingCartDTO> getAllCarts() throws Exception {
        Set<String> cartKeys = template.keys("cart:*");
        List<ShoppingCartDTO> carts = new ArrayList<>();
        ValueOperations<String, String> ops = template.opsForValue();
        if (cartKeys != null) {
            for (String key : cartKeys) {
                String cartData = ops.get(key);
                ShoppingCart cart = objectMapper.readValue(cartData, ShoppingCart.class);
                carts.add(shoppingCartMapper.toDTO(cart));
            }
        }
        return carts;
    }
}
