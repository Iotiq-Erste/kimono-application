package com.iotiq.application.service;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Customer;
import com.iotiq.application.domain.ProductDemand;
import com.iotiq.application.domain.Seller;
import com.iotiq.application.domain.enums.RequestStatus;
import com.iotiq.application.messages.customer.contact.BasicInfo;
import com.iotiq.application.messages.productdemand.ProductDemandDto;
import com.iotiq.application.messages.productdemand.ProductDemandRequest;
import com.iotiq.application.messages.productdemand.ProductDemandUpdateRequest;
import com.iotiq.application.messages.productdemand.SellerProductDemandUpdateRequest;
import com.iotiq.application.repository.ProductDemandRepository;
import com.iotiq.commons.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductDemandService {
    private final ProductDemandRepository productDemandRepository;

    @Transactional
    public ProductDemand createProductDemand(ProductDemandRequest productDemandRequest, Customer customer) {
        ProductDemand productDemand = ModelMapperUtil.map(productDemandRequest, ProductDemand.class);
        productDemand.setCustomer(customer);
        return productDemandRepository.save(productDemand);
    }

    public Page<ProductDemandDto> getProductDemandsForSeller(Pageable pageable) {
        return productDemandRepository.findAllByIsActiveTrueAndSellerIsNull(pageable).map(productDemand -> {
            ProductDemandDto demandDto = ModelMapperUtil.map(productDemand, ProductDemandDto.class);
            demandDto.setCustomerBasicInfo(createBasicInfo(productDemand));
            return demandDto;
        });
    }

    public Page<ProductDemandDto> getProductDemandsForCurrentCustomer(Pageable pageable, Customer customer) {
        return productDemandRepository.findAllByCustomerAndIsActiveTrue(customer, pageable).map(
                productDemand -> {
                    ProductDemandDto demandDto = ModelMapperUtil.map(productDemand, ProductDemandDto.class);
                    demandDto.setCustomerBasicInfo(createBasicInfo(productDemand));
                    return demandDto;
                }
        );
    }

    @Transactional
    public void updateProductDemand(UUID id, ProductDemandUpdateRequest updateRequest, Customer customer) {
        ProductDemand productDemand = findProductDemandOfCurrentCustomerById(id, customer);

        ModelMapperUtil.map(updateRequest, productDemand);
        productDemandRepository.save(productDemand);
    }

    @Transactional
    public void updateDemandStatusBySeller(UUID id, SellerProductDemandUpdateRequest updateRequest, Seller seller) {
        ProductDemand productDemand = productDemandRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(ProductDemand.ENTITY_NAME, id));

        if (productDemand.getSeller() == null && updateRequest.getRequestStatus().equals(RequestStatus.IN_PROGRESS)) {
            productDemand.setSeller(seller);
        } else if (productDemand.getSeller() != null && Objects.requireNonNull(productDemand.getSeller().getId()).equals(seller.getId())) {
            productDemand.setSeller(updateRequest.getRequestStatus().equals(RequestStatus.CANCELLED) ? null : seller);
        } else {
            throw new EntityNotFoundException(ProductDemand.ENTITY_NAME, id);
        }

        productDemand.setStatus(updateRequest.getRequestStatus());
        productDemandRepository.save(productDemand);
    }

    @Transactional
    public void deleteProductDemand(UUID id, Customer customer) {
        ProductDemand productDemand = findProductDemandOfCurrentCustomerById(id, customer);
        productDemand.setActive(false);
        productDemandRepository.save(productDemand);
    }

    private ProductDemand findProductDemandOfCurrentCustomerById(UUID id, Customer customer) {
        return productDemandRepository.findByIdAndCustomerAndIsActiveTrue(id, customer).orElseThrow(() ->
                new EntityNotFoundException(ProductDemand.ENTITY_NAME, id));
    }

    public ProductDemand getProductDemandOfCustomerByID(UUID id, Customer customer) {
        Optional<ProductDemand> productDemand = productDemandRepository.findById(id);

        if (productDemand.isEmpty() || !Objects.equals(productDemand.get().getCustomer().getId(), customer.getId())) {
            throw new EntityNotFoundException(ProductDemand.ENTITY_NAME, id);
        }
        return productDemand.get();
    }

    public ProductDemandDto getProductDemand(UUID id) {
        return ModelMapperUtil.map(productDemandRepository.findByIdAndSellerIsNull(id).orElseThrow(() ->
                new EntityNotFoundException(ProductDemand.ENTITY_NAME, id)), ProductDemandDto.class);
    }

    private BasicInfo createBasicInfo(ProductDemand productDemand) {
        BasicInfo basicInfo = new BasicInfo();
        basicInfo.setFirstName(productDemand.getCustomer().getUser().getPersonalInfo().getFirstName());
        basicInfo.setLastName(productDemand.getCustomer().getUser().getPersonalInfo().getLastName());
        basicInfo.setPhoneNumber(productDemand.getCustomer().getUser().getPersonalInfo().getPhoneNumber());
        basicInfo.setEmail(productDemand.getCustomer().getUser().getPersonalInfo().getEmail());
        return basicInfo;
    }
}
