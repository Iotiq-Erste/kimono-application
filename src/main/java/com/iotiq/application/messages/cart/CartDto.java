package com.iotiq.application.messages.cart;

import com.iotiq.application.domain.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;


public class CartDto {

    private UUID id;

    private UUID owner;

    private List<Product> products;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
