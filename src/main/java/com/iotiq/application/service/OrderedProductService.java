package com.iotiq.application.service;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.OrderedProduct;
import com.iotiq.application.domain.Seller;
import com.iotiq.application.messages.customer.contact.BasicInfo;
import com.iotiq.application.messages.orderedproduct.OrderedProductDto;
import com.iotiq.application.repository.OrderedProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderedProductService {

    private final OrderedProductRepository orderedProductRepository;

    public List<OrderedProductDto> getOrderedProducts(Seller seller) {
        return orderedProductRepository.findAllBySeller(seller).stream().map(this::toOrderedProductDto).collect(Collectors.toList());
    }

    public OrderedProductDto toOrderedProductDto(OrderedProduct orderedProduct) {
        OrderedProductDto orderedProductDto = ModelMapperUtil.map(orderedProduct, OrderedProductDto.class);
        orderedProductDto.setOrderDate(orderedProduct.getOrder().getOrderDate());
        orderedProductDto.setOrderNumber(orderedProduct.getOrder().getOrderNumber());
        orderedProductDto.setDeliveryAddress(orderedProduct.getOrder().getDeliveryAddress());
        orderedProductDto.setDeliveryType(orderedProduct.getOrder().getDeliveryType());
        orderedProductDto.setDeliveryAddressType(orderedProduct.getOrder().getDeliveryAddressType());
        orderedProductDto.setCustomerBasicInfo(ModelMapperUtil.map(orderedProduct.getOrder().getCustomer().getUser().getPersonalInfo(), BasicInfo.class));
        return orderedProductDto;
    }

}
