package com.iotiq.application.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.iotiq.application.domain.enums.DeliveryStatus;
import com.iotiq.commons.domain.BaseAbstractAuditingEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "order_table")
public class Order extends BaseAbstractAuditingEntity<UUID> {

    public static final String ENTITY_NAME = "order";

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonIgnore
    private Customer customer;

    @Column(nullable = false)
    private String orderNumber;

    private BigDecimal cargoPrice;

    private BigDecimal totalDiscount = BigDecimal.ZERO;

    private BigDecimal totalPrice;

    private String deliveryType;

    private String deliveryAddressType;

    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    private LocalDateTime deliveryStatusDate;

    @Column(nullable = false)
    private LocalDateTime orderDate;

    @Column(nullable = false)
    private LocalDateTime orderUtcDate;

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderedProduct> orderedProducts;

    private boolean isVisible = true;

    @PrePersist
    public void setOrderNumber() {
        this.orderNumber = StringUtils.left(String.valueOf(getId()), 5).toUpperCase();
    }
}
