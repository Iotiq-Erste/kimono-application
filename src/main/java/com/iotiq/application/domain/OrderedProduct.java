package com.iotiq.application.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
@AllArgsConstructor
@NoArgsConstructor
public class OrderedProduct extends AbstractPersistable<UUID> {

    private UUID productId;

    private String title;

    private Price price;

    private String imageUrl;

    private String sellerName;

    private UUID sellerId;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
