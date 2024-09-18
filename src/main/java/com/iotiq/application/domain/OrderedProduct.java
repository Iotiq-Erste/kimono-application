package com.iotiq.application.domain;

import com.iotiq.commons.domain.BaseAbstractAuditingEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderedProduct extends BaseAbstractAuditingEntity<UUID> {

    private UUID productId;

    private String title;

    private Price price;

    private String imageUrl;

    private String sellerName;

    private UUID sellerId;

    @ManyToOne
    private Order order;
}
