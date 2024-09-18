package com.iotiq.application.service;

import com.iotiq.application.domain.CartItem;
import com.iotiq.application.repository.CartItemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;

    @Transactional
    public void save(List<CartItem> cartItems) { cartItemRepository.saveAll(cartItems);}

}
