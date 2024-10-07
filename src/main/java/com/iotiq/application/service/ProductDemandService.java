package com.iotiq.application.service;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Customer;
import com.iotiq.application.domain.ProductDemand;
import com.iotiq.application.exception.productdemandexceptions.ProductDemandNotFoundException;
import com.iotiq.application.messages.customer.contact.BasicInfo;
import com.iotiq.application.messages.productdemand.ProductDemandDetailDto;
import com.iotiq.application.messages.productdemand.ProductDemandDto;
import com.iotiq.application.messages.productdemand.ProductDemandRequest;
import com.iotiq.application.repository.ProductDemandRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductDemandService {
    private final ProductDemandRepository productDemandRepository;
    private final CustomerService customerService;

    public ProductDemand createProductDemand(ProductDemandRequest productDemandRequest) {
        Customer customer = customerService.getCurrentCustomer();
        ProductDemand productDemand = ModelMapperUtil.map(productDemandRequest, ProductDemand.class);
        productDemand.setCustomer(customer);
        productDemand.setActive(true);
        return productDemandRepository.save(productDemand);
    }

    public List<ProductDemandDetailDto> getProductDemands() {
        return productDemandRepository.findAllByIsActiveTrue().stream().map(productDemand -> {
            ProductDemandDetailDto detailDto = ModelMapperUtil.map(productDemand, ProductDemandDetailDto.class);
            BasicInfo basicInfo = new BasicInfo();
            basicInfo.setFirstName(productDemand.getCustomer().getUser().getPersonalInfo().getFirstName());
            basicInfo.setLastName(productDemand.getCustomer().getUser().getPersonalInfo().getLastName());
            basicInfo.setPhoneNumber(productDemand.getCustomer().getUser().getPersonalInfo().getPhoneNumber());
            basicInfo.setEmail(productDemand.getCustomer().getUser().getPersonalInfo().getEmail());
            detailDto.setCustomerBasicInfo(basicInfo);
            return detailDto;
        }).collect(Collectors.toList());
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
        return productDemandRepository.findByIdAndCustomerAndIsActiveTrue(id, customer).orElseThrow(() ->
                new ProductDemandNotFoundException("Product demand could not found"));
    }
}
