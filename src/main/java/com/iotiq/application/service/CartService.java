package com.iotiq.application.service;

import com.iotiq.application.domain.Cart;
import com.iotiq.application.domain.CartItem;
import com.iotiq.application.domain.Customer;
import com.iotiq.application.messages.cart.CartDto;
import com.iotiq.application.messages.cart.CartUpdateRequest;
import com.iotiq.application.messages.cartitem.CartItemDto;
import com.iotiq.application.repository.CartRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;

    public CartDto getCart(Customer customer) {
        return customer.getCart().toDto();
    }

    @Transactional
    public void update(CartUpdateRequest request, Customer customer) {
        Cart cart = customer.getCart();
        cart.getCartItems().clear();
        cart.getCartItems().addAll(toCartItemList(request.getCartItems(), cart));

        cartRepository.save(cart);
    }

    public List<CartItem> toCartItemList(List<CartItemDto> cartItemDtos, Cart cart) {
        return cartItemDtos.stream().map(cartItemDto -> {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(productService.getOne(cartItemDto.getProductId()));
            cartItem.setQuantity(cartItemDto.getQuantity());
            cartItem.setCart(cart);
            return cartItem;
        }).toList();
    }


}
