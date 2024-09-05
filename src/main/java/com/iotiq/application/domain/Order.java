package com.iotiq.application.domain;

import com.iotiq.application.domain.enums.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

//@Entity
@Getter
@Setter
public class Order extends AbstractPersistable<UUID> {

    private UUID userId;

    private String orderNumber;

    private BigDecimal cargoPrice;

    private BigDecimal totalDiscount;

    private BigDecimal totalPrice;

    private String deliveryType;

    private String deliveryAddressType;

    private String deliveryAddress;

    private DeliveryStatus deliveryStatus;

    private Date deliveryStatusDate;

    private Date orderDate;

    private Cart cart;

}
