package com.iotiq.application.messages.product;

import com.iotiq.application.domain.Composition;
import com.iotiq.application.domain.*;
import com.iotiq.application.domain.enums.*;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductUpdateRequest {

    private String title;
    private String description;
    @Valid
    private Price price;
    private String imageUrl;
    private AgeGroup ageGroup;
    private ApplicationAreaGroup applicationAreaGroup;
    private String brand;
    private Category category;
    private List<Certification> certifications;
    private Color color;
    private Composition composition;
    private Design design;
    private List<DesignBodyPart> designBodyParts;
    private List<FiberType> fiberTypes;
    private Gender gender;
    private Haptics haptics;
    private MaterialBehavior materialBehavior;
    @Valid
    private MaterialParameter materialParameter;
    private Motif motif;
    private Neurodermatitis neurodermatitis;
    private OekotexStandard oekotexStandard;
    private PriceRange priceRange;
    private Rating rating;
    private List<Size> sizes;
    private List<SpecificBodyPart> specificBodyParts;
    private List<SpecificFunctionality> specificFunctionalities;
    private Sustainability sustainability;
    private List<Skill> skills;
}
