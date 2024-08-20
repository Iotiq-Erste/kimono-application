package com.iotiq.application.entity.product;

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
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Product extends AbstractPersistable<UUID> {

    private String title;
    private String description;
    private String price;
    private String imageUrl;
    @Embedded
    private AgeGroup ageGroup;
    @Embedded
    private ApplicationArea applicationArea;
    @Embedded
    private Brand brand;
    @Embedded
    private Category category;
    @Embedded
    private Certification certification;
    @Embedded
    private Color color;
    @Embedded
    private Composition composition;
    @Embedded
    private Design design;
    @Embedded
    private DesignBodyPart designBodyPart;
    @Embedded
    private Fiber fiber;
    @Embedded
    private Gender gender;
    @Embedded
    private Haptics haptics;
    @Embedded
    private MaterialBehavior materialBehavior;
    @Embedded
    private MaterialParameter materialParameters;
    @Embedded
    private Motif motif;
    @Embedded
    private Neurodermatitis neurodermatitis;
    @Embedded
    private OekotexStandard oekotexStandard;
    @Embedded
    private PriceRange priceRange;
    @Embedded
    private Rating rating;
    @Embedded
    private Size size;
    @Embedded
    private SpecificBodyPart specificBodyPart;
    @Embedded
    private SpecificFunctionality specificFunctionality;
    @Embedded
    private Sustainability sustainability;

}
