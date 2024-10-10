package com.iotiq.application.service;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Customer;
import com.iotiq.application.domain.ProductDemand;
import com.iotiq.application.domain.Seller;
import com.iotiq.application.messages.customer.contact.BasicInfo;
import com.iotiq.application.messages.productdemand.ProductDemandDto;
import com.iotiq.application.messages.productdemand.ProductDemandRequest;
import com.iotiq.application.messages.productdemand.ProductDemandUpdateRequest;
import com.iotiq.application.repository.ProductDemandRepository;
import com.iotiq.commons.exceptions.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductDemandService {
    private final ProductDemandRepository productDemandRepository;
    private final CustomerService customerService;
    private final SellerService sellerService;

    @Transactional
    public ProductDemand createProductDemand(ProductDemandRequest productDemandRequest) {
        ProductDemand productDemand = ModelMapperUtil.map(productDemandRequest, ProductDemand.class);
        productDemand.setCustomer(customerService.getCurrentCustomer());
        return productDemandRepository.save(productDemand);
    }

    public List<ProductDemandDto> getProductDemands(Authentication authentication) throws RoleNotFoundException {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_COMPANY"))) {
            return getProductDemandsForSeller();
        } else if (authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_CUSTOMER"))) {
            return getProductDemandsForCurrentCustomer();
        }
        throw new RoleNotFoundException("Inappropriate role");
    }


    public List<ProductDemandDto> getProductDemandsForSeller() {
        return productDemandRepository.findAllByIsActiveTrueAndSellerIsNull().stream().map(productDemand -> {
            ProductDemandDto detailDto = ModelMapperUtil.map(productDemand, ProductDemandDto.class);
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

    @Transactional
    public void updateProductDemand(UUID id, ProductDemandUpdateRequest updateRequest) {

        if (sellerService.getCurrentSeller().getId() != updateRequest.getSellerID()) {
            throw new EntityNotFoundException(Seller.ENTITY_NAME, updateRequest.getSellerID());
        }
        ProductDemand productDemand = findByIdAndCustomerAndIsActiveTrue(id);
        ModelMapperUtil.map(updateRequest, productDemand);
        productDemand.setSeller(sellerService.getCurrentSeller());
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
                new EntityNotFoundException(ProductDemand.ENTITY_NAME, id));
    }

    public ProductDemandDto getProductDemandByID(UUID id) {
        return ModelMapperUtil.map(productDemandRepository.findById(id), ProductDemandDto.class);
    }
}
