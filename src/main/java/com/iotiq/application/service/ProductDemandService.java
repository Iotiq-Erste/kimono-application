package com.iotiq.application.service;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Customer;
import com.iotiq.application.domain.ProductDemand;
import com.iotiq.application.exception.productdemandexceptions.ProductDemandNotFoundException;
import com.iotiq.application.messages.productdemand.ProductDemandDto;
import com.iotiq.application.messages.productdemand.ProductDemandRequest;
import com.iotiq.application.repository.ProductDemandRepository;
import com.iotiq.application.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductDemandService {
    private final ProductDemandRepository productDemandRepository;
    private final ProductRepository productRepository;
    private final CustomerService customerService;

    public ProductDemand createProductDemand(ProductDemandRequest productDemandRequest) {
        Customer customer = customerService.getCurrentCustomer();
        ProductDemand productDemand = ModelMapperUtil.map(productDemandRequest, ProductDemand.class);
        productDemand.setCustomer(customer);
        productDemand.setActive(true);
        return productDemandRepository.save(productDemand);
    }

    public List<ProductDemandDto> getProductDemands() {
        return ModelMapperUtil.map(productDemandRepository.findAllByIsActiveTrue(), ProductDemandDto.class);
    }

    public List<ProductDemandDto> getProductDemandsForCurrentCustomer() {
        Customer customer = customerService.getCurrentCustomer();
        List<ProductDemand> demands = productDemandRepository.findAllByCustomerAndIsActiveTrue(customer).orElse(new ArrayList<>());
        return ModelMapperUtil.map(demands, ProductDemandDto.class);
    }

    public ProductDemandDto getProductDemandForCurrentCustomer(UUID id) {
        ProductDemand productDemand = findByIdAndCustomerAndIsActiveTrue(id);

        return ModelMapperUtil.map(productDemand, ProductDemandDto.class);
    }

    @Transactional
    public void updateProductDemand(UUID id, ProductDemandRequest productDemandRequest) {
        Customer customer = customerService.getCurrentCustomer();
        ProductDemand productDemand = findByIdAndCustomerAndIsActiveTrue(id);
        ModelMapperUtil.map(productDemandRequest, productDemand);
        productDemandRepository.save(productDemand);
    }

    @Transactional
    public void deleteProductDemand(UUID id) {
        ProductDemand productDemand = findByIdAndCustomerAndIsActiveTrue(id);
        productDemand.setActive(false);
        productDemandRepository.save(productDemand);
    }

    private ProductDemand findByIdAndCustomerAndIsActiveTrue(UUID id) {
        Customer customer = customerService.getCurrentCustomer();
        return productDemandRepository.findByIdAndCustomerAndIsActiveTrue(id,customer).orElseThrow(() ->
                new ProductDemandNotFoundException("Product demand could not found"));
    }
}
