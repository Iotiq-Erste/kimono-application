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
import com.iotiq.user.domain.Person;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.internal.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductDemandService {
    private final ProductDemandRepository productDemandRepository;
    private static final Logger log = LoggerFactory.getLogger(ProductDemandService.class);

    @Transactional
    public ProductDemand createProductDemand(ProductDemandRequest productDemandRequest, Customer customer) {
        ProductDemand productDemand = ModelMapperUtil.map(productDemandRequest, ProductDemand.class);
        productDemand.setCustomer(customer);

        setBrand(productDemand);

        ProductDemand createdProductDemand = productDemandRepository.save(productDemand);

        log.info("Product demand {} created", createdProductDemand.getId());

        return createdProductDemand;
    }

    public Page<ProductDemandDto> getProductDemandsForSeller(Pageable pageable, Seller seller) {
        return productDemandRepository.
                findActiveUnassignedAndMatchingSkills(pageable, seller.getSkills()).map(productDemand -> {
                    ProductDemandDto demandDto = ModelMapperUtil.map(productDemand, ProductDemandDto.class);
                    demandDto.setCustomerBasicInfo(createBasicInfo(productDemand));
                    return demandDto;
                });
    }

    public Page<ProductDemandDto> getAssignedProductDemandsForSeller(Pageable pageable, Seller seller) {
        return productDemandRepository.findAllBySeller(pageable, seller).map(productDemand -> {
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

        if (productDemand.getStatus().equals(RequestStatus.COMPLETED) || productDemand.getStatus().equals(RequestStatus.CANCELLED)
                || productDemand.getStatus().equals(RequestStatus.IN_PROGRESS)) {
            throw new EntityNotFoundException(ProductDemand.ENTITY_NAME);
        }

        ModelMapperUtil.map(updateRequest, productDemand);

        productDemandRepository.save(productDemand);

        log.info("Product demand {} updated", productDemand.getId());
    }

    /**
     * Updates the status of a product demand based on the provided seller's request.
     *
     * @param id            the UUID of the product demand to be updated
     * @param updateRequest the request containing the new status for the product demand
     * @param seller        the seller performing the update
     * @throws EntityNotFoundException if the product demand with the given ID is not found
     */
    @Transactional
    public void updateDemandStatusBySeller(UUID id, SellerProductDemandUpdateRequest updateRequest, Seller seller) {

        ProductDemand productDemand = productDemandRepository.findActiveByIdAndMatchingSkills(id, seller.getSkills())
                .orElseThrow(() -> new EntityNotFoundException(ProductDemand.ENTITY_NAME, id));
        if (productDemand.getStatus().equals(RequestStatus.COMPLETED) || productDemand.getStatus().equals(RequestStatus.CANCELLED)) {
            throw new EntityNotFoundException(ProductDemand.ENTITY_NAME);
        }
        updateSellerInfo(productDemand, updateRequest.getStatus(), seller);
        productDemand.setStatus(
                updateRequest.getStatus() == RequestStatus.CANCELLED ?
                        RequestStatus.PENDING : updateRequest.getStatus());
        productDemandRepository.save(productDemand);
    }

    /**
     * Updates the seller information associated with a given product demand based on the request status.
     *
     * @param productDemand the product demand being updated
     * @param status        the current status of the request
     * @param seller        the seller information to be updated in the product demand
     * @throws EntityNotFoundException if the product demand does not have an existing seller and the status is not in progress,
     *                                 or if the seller id does not match the existing seller id in the product demand.
     */
    private void updateSellerInfo(ProductDemand productDemand, RequestStatus status, Seller seller) {
        UUID productDemandSellerId = productDemand.getSeller() == null ? null : productDemand.getSeller().getId();

        if (productDemandSellerId == null && status.equals(RequestStatus.IN_PROGRESS)) {
            productDemand.setSeller(seller);
        } else if (productDemandSellerId != null && productDemandSellerId.equals(seller.getId())) {
            productDemand.setSeller(status.equals(RequestStatus.CANCELLED) ? null : seller);
        } else {
            log.error("Seller {} cannot update the status for this product demand {}.", seller.getId(), productDemand.getId());
            throw new EntityNotFoundException(ProductDemand.ENTITY_NAME, productDemand.getId());
        }
    }

    @Transactional
    public void deleteProductDemand(UUID id, Customer customer) {
        ProductDemand productDemand = findProductDemandOfCurrentCustomerById(id, customer);

        if (productDemand.getStatus().equals(RequestStatus.COMPLETED)) {
            throw new EntityNotFoundException(ProductDemand.ENTITY_NAME);
        }

        if (Objects.nonNull(productDemand.getSeller())) {
            productDemand.setStatus(RequestStatus.CANCELLED);
        }
        productDemand.setActive(false);
        productDemandRepository.save(productDemand);
    }

    private ProductDemand findProductDemandOfCurrentCustomerById(UUID id, Customer customer) {
        return productDemandRepository.findByIdAndCustomerAndIsActiveTrue(id, customer).orElseThrow(() ->
                new EntityNotFoundException(ProductDemand.ENTITY_NAME, id));
    }

    public ProductDemandDto getProductDemandOfCustomerByID(UUID id, Customer customer) {
        ProductDemand productDemand = findProductDemandOfCurrentCustomerById(id, customer);

        ProductDemandDto productDemandDto = ModelMapperUtil.map(productDemand, ProductDemandDto.class);
        productDemandDto.setCustomerBasicInfo(ModelMapperUtil.map(Objects.requireNonNullElseGet(productDemand.getCustomer().getUser().getPersonalInfo(), Person::new), BasicInfo.class));

        return productDemandDto;
    }

    public ProductDemandDto getProductDemand(UUID id, Seller seller) {
        ProductDemand productDemand = productDemandRepository.findByIdAndSellerAndMatchingSkills(id, seller, seller.getSkills()).orElseThrow(() ->
                new EntityNotFoundException(ProductDemand.ENTITY_NAME, id));
        ProductDemandDto productDemandDto = ModelMapperUtil.map(productDemand, ProductDemandDto.class);
        ModelMapperUtil.map(productDemand, ProductDemandDto.class);
        productDemandDto.setCustomerBasicInfo(ModelMapperUtil.map(Objects.requireNonNullElseGet(productDemand.getCustomer().getUser().getPersonalInfo(), Person::new), BasicInfo.class));
        return productDemandDto;
    }

    private BasicInfo createBasicInfo(ProductDemand productDemand) {
        BasicInfo basicInfo = new BasicInfo();
        basicInfo.setFirstName(productDemand.getCustomer().getUser().getPersonalInfo().getFirstName());
        basicInfo.setLastName(productDemand.getCustomer().getUser().getPersonalInfo().getLastName());
        basicInfo.setPhoneNumber(productDemand.getCustomer().getUser().getPersonalInfo().getPhoneNumber());
        basicInfo.setEmail(productDemand.getCustomer().getUser().getPersonalInfo().getEmail());
        return basicInfo;
    }

    private void setBrand(ProductDemand productDemand) {

        if (!StringUtils.hasText(productDemand.getBrand())) {
            String result = "Product Demand " + (productDemandRepository.findMaxUnbrandedProductDemand() + 1);
            productDemand.setBrand(result);
        }
    }
}
