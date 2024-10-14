package com.iotiq.application.service;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.messages.customer.contact.BasicInfo;
import com.iotiq.application.messages.orderedproduct.OrderedProductDto;
import com.iotiq.application.repository.OrderedProductRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderedProductService {

    private final OrderedProductRepository orderedProductRepository;
    private final SellerService sellerService;

    public OrderedProductService(OrderedProductRepository orderedProductRepository, @Lazy SellerService sellerService) {
        this.orderedProductRepository = orderedProductRepository;
        this.sellerService = sellerService;
    }

    public List<OrderedProductDto> getOrderedProducts() {
        return orderedProductRepository.findAllBySeller(sellerService.getCurrentSeller()).stream().map(
                orderedProduct -> {
                    OrderedProductDto orderedProductDto = ModelMapperUtil.map(orderedProduct, OrderedProductDto.class);
                    orderedProductDto.setOrderDate(orderedProduct.getOrder().getOrderDate());
                    orderedProductDto.setOrderNumber(orderedProduct.getOrder().getOrderNumber());
                    orderedProductDto.setDeliveryAddress(orderedProduct.getOrder().getDeliveryAddress());
                    orderedProductDto.setDeliveryType(orderedProduct.getOrder().getDeliveryType());
                    orderedProductDto.setDeliveryAddressType(orderedProduct.getOrder().getDeliveryAddressType());
                    orderedProductDto.setCustomerBasicInfo(ModelMapperUtil.map(orderedProduct.getOrder().getCustomer().getUser().getPersonalInfo(), BasicInfo.class));
                    return orderedProductDto;
                }
        ).collect(Collectors.toList());
    }


}
