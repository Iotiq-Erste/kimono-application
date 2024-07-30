package com.iotiq.application.messages.product;

import com.iotiq.application.entity.product.productFilters.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;


public record ProductCreateRequest(@NotBlank String title,
                                   @NotEmpty String description,
                                   @NotBlank String price,
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

}
