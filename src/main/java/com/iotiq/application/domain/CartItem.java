package com.iotiq.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItem extends AbstractPersistable<UUID> {

    @ManyToOne
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Cart cart;

    private Long quantity;

}
