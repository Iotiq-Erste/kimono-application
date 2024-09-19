package com.iotiq.application.service;

import com.iotiq.application.domain.Cart;
import com.iotiq.application.domain.CartItem;
import com.iotiq.application.messages.cartitem.CartItemDto;
import com.iotiq.application.messages.cart.CartUpdateRequest;
import com.iotiq.application.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CustomerService customerService;
    private final ProductService productService;

    public Cart getCart() {
        return customerService.getCurrentCustomer().getCart();
    }

    @Transactional
    public void update(CartUpdateRequest request) {
        Cart cart = getCart();
        cart.setLastModifiedDate(Instant.now());
        cart.getCartItems().clear();
        cart.getCartItems().addAll(toCartItemList(request.getCartItems(), cart));

        cartRepository.save(cart);
    }

    public List<CartItem> toCartItemList(List<CartItemDto> cartItemDtos, Cart cart){
        return cartItemDtos.stream().map(cartItemDto -> {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(productService.getOne(cartItemDto.getProductId()));
            cartItem.setQuantity(cartItemDto.getQuantity());
            cartItem.setCart(cart);
            return cartItem;
        }).toList();
    }

}
