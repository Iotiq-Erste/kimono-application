package com.iotiq.application.messages.product;

import com.iotiq.application.entity.product.productFilters.AgeGroup;
import com.iotiq.application.entity.product.productFilters.ApplicationArea;
import com.iotiq.application.entity.product.productFilters.Brand;
import com.iotiq.application.entity.product.productFilters.Category;
import com.iotiq.application.entity.product.productFilters.Certification;
import com.iotiq.application.entity.product.productFilters.Color;
import com.iotiq.application.entity.product.productFilters.Composition;
import com.iotiq.application.entity.product.productFilters.Design;
import com.iotiq.application.entity.product.productFilters.DesignBodyPart;
import com.iotiq.application.entity.product.productFilters.Fiber;
import com.iotiq.application.entity.product.productFilters.Gender;
import com.iotiq.application.entity.product.productFilters.Haptics;
import com.iotiq.application.entity.product.productFilters.MaterialBehavior;
import com.iotiq.application.entity.product.productFilters.MaterialParameter;
import com.iotiq.application.entity.product.productFilters.Motif;
import com.iotiq.application.entity.product.productFilters.Neurodermatitis;
import com.iotiq.application.entity.product.productFilters.OekotexStandard;
import com.iotiq.application.entity.product.productFilters.PriceRange;
import com.iotiq.application.entity.product.productFilters.Rating;
import com.iotiq.application.entity.product.productFilters.Size;
import com.iotiq.application.entity.product.productFilters.SpecificBodyPart;
import com.iotiq.application.entity.product.productFilters.SpecificFunctionality;
import com.iotiq.application.entity.product.productFilters.Sustainability;

public record ProductUpdateRequest(String title,
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
    }
