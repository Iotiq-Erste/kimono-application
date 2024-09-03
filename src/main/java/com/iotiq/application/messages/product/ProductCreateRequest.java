package com.iotiq.application.messages.product;

import com.iotiq.application.domain.AgeGroup;
import com.iotiq.application.domain.ApplicationAreaGroup;
import com.iotiq.application.domain.Composition;
import com.iotiq.application.domain.Design;
import com.iotiq.application.domain.Haptics;
import com.iotiq.application.domain.MaterialBehavior;
import com.iotiq.application.domain.Price;
import com.iotiq.application.domain.Sustainability;
import com.iotiq.application.domain.enums.Brand;
import com.iotiq.application.domain.enums.Category;
import com.iotiq.application.domain.enums.Certification;
import com.iotiq.application.domain.enums.Color;
import com.iotiq.application.domain.enums.DesignBodyPart;
import com.iotiq.application.domain.enums.FiberType;
import com.iotiq.application.domain.enums.Gender;
import com.iotiq.application.domain.enums.Material;
import com.iotiq.application.domain.enums.MaterialParameter;
import com.iotiq.application.domain.enums.Motif;
import com.iotiq.application.domain.enums.Neurodermatitis;
import com.iotiq.application.domain.enums.OekotexStandard;
import com.iotiq.application.domain.enums.PriceRange;
import com.iotiq.application.domain.enums.Rating;
import com.iotiq.application.domain.enums.Size;
import com.iotiq.application.domain.enums.SpecificBodyPart;
import com.iotiq.application.domain.enums.SpecificFunctionality;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ProductCreateRequest{

    @NotBlank
    private String title;
    @NotEmpty
    private String description;
    @NotNull
    private Price price;
    private String imageUrl;
    private AgeGroup ageGroup;
    private ApplicationAreaGroup applicationAreaGroup;
    @NotNull
    private Brand brand;
    @NotNull
    private Category category;
    private List<Certification> certifications;
    @NotNull
    private Color color;
    private Composition composition;
    private Design design;
    private List<DesignBodyPart> designBodyParts;
    private List<FiberType> fiberTypes;
    private Gender gender;
    private Haptics haptics;
    private Material material;
    private MaterialBehavior materialBehavior;
    private MaterialParameter materialParameter;
    private Motif motif;
    private Neurodermatitis neurodermatitis;
    private OekotexStandard oekotexStandard;
    private PriceRange priceRange;
    private Rating rating;
    @NotNull
    private List<Size> sizes;
    private List<SpecificBodyPart> specificBodyParts;
    private List<SpecificFunctionality> specificFunctionalities;
    private Sustainability sustainability;

}
