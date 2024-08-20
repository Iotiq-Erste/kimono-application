package com.iotiq.application.entity.product;

import com.iotiq.application.entity.product.productFilters.*;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.UUID;

@Getter
@Setter
@Entity
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

    public Product (String title, String description, String price, String imageUrl, AgeGroup ageGroup,
                    ApplicationArea applicationArea, Brand brand, Category category, Certification certification,
                    Color color, Composition composition, Design design, DesignBodyPart designBodyPart, Fiber fiber,
                    Gender gender, Haptics haptics, MaterialBehavior materialBehavior,
                    MaterialParameter materialParameters, Motif motif, Neurodermatitis neurodermatitis,
                    OekotexStandard oekotexStandard, PriceRange priceRange, Rating rating, Size size,
                    SpecificBodyPart specificBodyPart, SpecificFunctionality specificFunctionality,
                    Sustainability sustainability) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.ageGroup = ageGroup;
        this.applicationArea = applicationArea;
        this.brand = brand;
        this.category = category;
        this.certification = certification;
        this.color = color;
        this.composition = composition;
        this.design = design;
        this.designBodyPart = designBodyPart;
        this.fiber = fiber;
        this.gender = gender;
        this.haptics = haptics;
        this.materialBehavior = materialBehavior;
        this.materialParameters = materialParameters;
        this.motif = motif;
        this.neurodermatitis = neurodermatitis;
        this.oekotexStandard = oekotexStandard;
        this.priceRange = priceRange;
        this.rating = rating;
        this.size = size;
        this.specificBodyPart = specificBodyPart;
        this.specificFunctionality = specificFunctionality;
        this.sustainability = sustainability;
    }

    public Product() {

    }
}
