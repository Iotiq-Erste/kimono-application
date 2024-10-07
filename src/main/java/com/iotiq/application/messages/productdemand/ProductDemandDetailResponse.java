package com.iotiq.application.messages.productdemand;

import com.iotiq.application.domain.AgeGroup;
import com.iotiq.application.domain.ApplicationAreaGroup;
import com.iotiq.application.domain.Composition;
import com.iotiq.application.domain.Design;
import com.iotiq.application.domain.Haptics;
import com.iotiq.application.domain.MaterialBehavior;
import com.iotiq.application.domain.Sustainability;
import com.iotiq.application.domain.enums.Category;
import com.iotiq.application.domain.enums.Certification;
import com.iotiq.application.domain.enums.Color;
import com.iotiq.application.domain.enums.DesignBodyPart;
import com.iotiq.application.domain.enums.FiberType;
import com.iotiq.application.domain.enums.Gender;
import com.iotiq.application.domain.enums.Motif;
import com.iotiq.application.domain.enums.Neurodermatitis;
import com.iotiq.application.domain.enums.OekotexStandard;
import com.iotiq.application.domain.enums.PriceRange;
import com.iotiq.application.domain.enums.Rating;
import com.iotiq.application.domain.enums.Size;
import com.iotiq.application.domain.enums.SpecificBodyPart;
import com.iotiq.application.domain.enums.SpecificFunctionality;
import com.iotiq.application.messages.customer.contact.BasicInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductDemandDetailResponse {

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

    private Motif motif;

    private Neurodermatitis neurodermatitis;

    private OekotexStandard oekotexStandard;

    private PriceRange priceRange;

    private Rating rating;

    private List<Size> sizes;

    private List<SpecificBodyPart> specificBodyParts;

    private List<SpecificFunctionality> specificFunctionalities;

    private Sustainability sustainability;

    private BasicInfo customerBasicInfo;
}
