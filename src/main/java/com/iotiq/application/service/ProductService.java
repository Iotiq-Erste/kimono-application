package com.iotiq.application.service;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.domain.Product;
import com.iotiq.application.exception.productexceptions.ProductNotFoundException;
import com.iotiq.application.messages.product.ProductCreateRequest;
import com.iotiq.application.messages.product.ProductCreateResponse;
import com.iotiq.application.messages.product.ProductFilter;
import com.iotiq.application.messages.product.ProductUpdateRequest;
import com.iotiq.application.repository.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    public Page<Product> getAll(@RequestParam ProductFilter filter, Sort sort) {
        return productRepository.findAll(filter.buildSpecification(), filter.buildPageable(sort));
    }

   public Product getOne(UUID id){
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
    }

    @Transactional
    public ProductCreateResponse createProduct (@Valid ProductCreateRequest request){

        Product saved = productRepository.save(ModelMapperUtil.map(request, Product.class));

        return new ProductCreateResponse(saved.getId());
        //ID APPLICATION LAYER MI OLMALI DATABASE MI YAPMALIYIM
    }

    public void delete (UUID id){
        productRepository.deleteById(id);
    }

    @Transactional
    public void update(UUID id, ProductUpdateRequest request){
        Product product = productRepository.findById(id).
                orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));

        ModelMapperUtil.map(request,product);

        productRepository.save(product);
    }

}
