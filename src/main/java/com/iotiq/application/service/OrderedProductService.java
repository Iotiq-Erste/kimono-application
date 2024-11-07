package com.iotiq.application.service;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Order;
import com.iotiq.application.domain.OrderedProduct;
import com.iotiq.application.domain.Seller;
import com.iotiq.application.messages.customer.contact.BasicInfo;
import com.iotiq.application.messages.orderedproduct.OrderedProductDto;
import com.iotiq.application.repository.OrderedProductRepository;
import com.iotiq.commons.message.request.PageableRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderedProductService {

    private final OrderedProductRepository orderedProductRepository;

    public Page<OrderedProductDto> getOrderedProducts(PageableRequest pageableRequest, Sort sort, Seller seller) {
        return orderedProductRepository.findAllBySeller(pageableRequest.buildPageable(sort), seller).map(this::toOrderedProductDto);
    }

    public OrderedProductDto toOrderedProductDto(OrderedProduct orderedProduct) {
        OrderedProductDto orderedProductDto = ModelMapperUtil.map(orderedProduct, OrderedProductDto.class);
        Order order = orderedProduct.getOrder();
        if (order != null) {
            orderedProductDto.setOrderDate(order.getOrderDate());
            orderedProductDto.setOrderNumber(order.getOrderNumber());
            orderedProductDto.setDeliveryAddress(order.getDeliveryAddress());
            orderedProductDto.setDeliveryType(order.getDeliveryType());
            orderedProductDto.setDeliveryAddressType(order.getDeliveryAddressType());

            if (order.getCustomer() != null && order.getCustomer().getUser() != null) {
                orderedProductDto.setCustomerBasicInfo(ModelMapperUtil.map(order.getCustomer().getUser().getPersonalInfo(), BasicInfo.class));
            }
        }
        return orderedProductDto;
    }
}
