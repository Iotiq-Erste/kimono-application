package com.iotiq.application.service;

import com.iotiq.application.config.ModelMapperUtil;
import com.iotiq.application.entity.product.Product;
import com.iotiq.application.exception.productExceptions.ProductNotFoundException;
import com.iotiq.application.messages.product.ProductCreateRequest;
import com.iotiq.application.messages.product.ProductCreateResponse;
import com.iotiq.application.messages.product.ProductFilter;
import com.iotiq.application.messages.product.ProductFilterRequest;
import com.iotiq.application.messages.product.ProductUpdateRequest;
import com.iotiq.application.repository.ProductRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getAll(ProductFilterRequest filterRequest, Sort sort) {
        ProductFilter productFilter = new ProductFilter();
        ModelMapperUtil.map(filterRequest,productFilter);
        return productRepository.findAll(productFilter.buildSpecification(), productFilter.buildPageable(sort));
    }

   public Product getOne(UUID id){
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found with ID: " + id));
    }

    @Transactional
    public ProductCreateResponse createProduct (@Valid ProductCreateRequest request){

        Product product = new Product();
        product.setTitle(request.title());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setImageUrl(request.imageUrl());
        product.setAgeGroup(request.ageGroup());
        product.setApplicationArea(request.applicationArea());
        product.setBrand(request.brand());
        product.setCategory(request.category());
        product.setCertification(request.certification());
        product.setColor(request.color());
        product.setComposition(request.composition());
        product.setDesign(request.design());
        product.setDesignBodyPart(request.designBodyPart());
        product.setFiber(request.fiber());
        product.setGender(request.gender());
        product.setHaptics(request.haptics());
        product.setMaterialBehavior(request.materialBehavior());
        product.setMaterialParameters(request.materialParameters());
        product.setMotif(request.motif());
        product.setNeurodermatitis(request.neurodermatitis());
        product.setOekotexStandard(request.oekotexStandard());
        product.setPriceRange(request.priceRange());
        product.setRating(request.rating());
        product.setSize(request.size());
        product.setSpecificBodyPart(request.specificBodyPart());
        product.setSpecificFunctionality(request.specificFunctionality());
        product.setSustainability(request.sustainability());

        Product saved = productRepository.save(product);

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

        if (!request.title().equals(product.getTitle())){
            product.setTitle(request.title());
        }
        if (!request.description().equals(product.getDescription())){
            product.setDescription(request.description());
        }
        if (!request.price().equals(product.getPrice())){
            product.setPrice(request.price());
        }
        if (!request.imageUrl().equals(product.getImageUrl())){
            product.setImageUrl(request.imageUrl());
        }
        if (request.ageGroup() != product.getAgeGroup()){
            product.setAgeGroup(request.ageGroup());
        }
        if (request.applicationArea() != product.getApplicationArea()){
            product.setApplicationArea(request.applicationArea());
        }
        if (request.brand() != product.getBrand()){
            product.setBrand(request.brand());
        }
        if (request.category() != product.getCategory()){
            product.setCategory(request.category());
        }
        if (request.certification() != product.getCertification()){
            product.setCertification(request.certification());
        }
        if (request.color() != product.getColor()){
            product.setColor(request.color());
        }
        if (request.composition() != product.getComposition()){
            product.setComposition(request.composition());
        }
        if (request.design() != product.getDesign()){
            product.setDesign(request.design());
        }
        if (request.designBodyPart() != product.getDesignBodyPart()){
            product.setDesignBodyPart(request.designBodyPart());
        }
        if (request.fiber() != product.getFiber()){
            product.setFiber(request.fiber());
        }
        if (request.gender() != product.getGender()){
            product.setGender(request.gender());
        }
        if (request.haptics() != product.getHaptics()){
            product.setHaptics(request.haptics());
        }
        if (request.materialBehavior() != product.getMaterialBehavior()){
            product.setMaterialBehavior(request.materialBehavior());
        }
        if (request.materialParameters() != product.getMaterialParameters()){
            product.setMaterialParameters(request.materialParameters());
        }
        if (request.motif() != product.getMotif()){
            product.setMotif(request.motif());
        }
        if (request.neurodermatitis() != product.getNeurodermatitis()){
            product.setNeurodermatitis(request.neurodermatitis());
        }
        if (request.oekotexStandard() != product.getOekotexStandard()){
            product.setOekotexStandard(request.oekotexStandard());
        }
        if (request.priceRange() != product.getPriceRange()){
            product.setPriceRange(request.priceRange());
        }
        if (request.rating() != product.getRating()){
            product.setRating(request.rating());
        }
        if (request.size() != product.getSize()){
            product.setSize(request.size());
        }
        if (request.specificBodyPart() != product.getSpecificBodyPart()){
            product.setSpecificBodyPart(request.specificBodyPart());
        }
        if (request.specificFunctionality() != product.getSpecificFunctionality()){
            product.setSpecificFunctionality(request.specificFunctionality());
        }
        if (request.sustainability() != product.getSustainability()){
            product.setSustainability(request.sustainability());
        }

        productRepository.save(product);
    }
}
