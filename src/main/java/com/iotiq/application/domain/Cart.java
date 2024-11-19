package com.iotiq.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.messages.cart.CartDto;
import com.iotiq.application.messages.cartitem.CartItemDetailDto;
import com.iotiq.commons.domain.BaseAbstractAuditingEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends BaseAbstractAuditingEntity<UUID> {

    @OneToOne
    @JoinColumn(name = "customer_id")
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> cartItems = new ArrayList<>();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cart that)) return false;
        if (this.getId() != null && that.getId() != null) return this.getId().equals(that.getId());

        return Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return 13;
    }

    public BigDecimal calculateTotalAmount() {
        return this.cartItems.stream()
                .map(cartItem -> BigDecimal.valueOf(cartItem.getQuantity())
                        .multiply(cartItem.getProduct().getPrice().getAmount()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public CartDto toDto () {
        CartDto cartDto = new CartDto();
        cartDto.setCartItems(ModelMapperUtil.map(this.cartItems, CartItemDetailDto.class));
        cartDto.setTotalAmount(calculateTotalAmount());
        return cartDto;
    }
}
