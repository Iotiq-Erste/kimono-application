package com.iotiq.application.messages.product;
import com.iotiq.application.entity.product.Product;
import com.iotiq.application.entity.product.productFilters.*;

import java.util.UUID;

//PRODUCT RESPONSE
public record ProductDto(
        UUID id,
        String title,
        String description,
        String price,
        String imageUrl,
        AgeGroup ageGroup,
        ApplicationArea applicationArea,
        Brand brand,
        Category category,
        Certification certification,
        Color color,
        Composition composition,
        Design design,
        DesignBodyPart designBodyPart,
        Fiber fiber,
        Gender gender,
        Haptics haptics,
        MaterialBehavior materialBehavior,
        MaterialParameter materialParameters,
        Motif motif,
        Neurodermatitis neurodermatitis,
        OekotexStandard oekotexStandard,
        PriceRange priceRange,
        Rating rating,
        Size size,
        SpecificBodyPart specificBodyPart,
        SpecificFunctionality specificFunctionality,
        Sustainability sustainability) {

    public static ProductDto of(Product product) {
        return new ProductDto(
                product.getId(),
                product.getTitle(),
                product.getDescription(),
                product.getPrice(),
                product.getImageUrl(),
                product.getAgeGroup(),
                product.getApplicationArea(),
                product.getBrand(),
                product.getCategory(),
                product.getCertification(),
                product.getColor(),
                product.getComposition(),
                product.getDesign(),
                product.getDesignBodyPart(),
                product.getFiber(),
                product.getGender(),
                product.getHaptics(),
                product.getMaterialBehavior(),
                product.getMaterialParameters(),
                product.getMotif(),
                product.getNeurodermatitis(),
                product.getOekotexStandard(),
                product.getPriceRange(),
                product.getRating(),
                product.getSize(),
                product.getSpecificBodyPart(),
                product.getSpecificFunctionality(),
                product.getSustainability());
    }
}
