package com.iotiq.application.messages.product;

import com.iotiq.application.domain.AgeGroup;
import com.iotiq.application.domain.ApplicationAreaGroup;
import com.iotiq.application.domain.Composition;
import com.iotiq.application.domain.Design;
import com.iotiq.application.domain.Haptics;
import com.iotiq.application.domain.MaterialBehavior;
import com.iotiq.application.domain.MaterialParameter;
import com.iotiq.application.domain.Price;
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
import com.iotiq.application.domain.enums.ProductCSVHeader;
import com.iotiq.application.domain.enums.Rating;
import com.iotiq.application.domain.enums.Size;
import com.iotiq.application.domain.enums.SpecificBodyPart;
import com.iotiq.application.domain.enums.SpecificFunctionality;
import jakarta.validation.Valid;
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
    @Valid
    private Price price;
    private String imageUrl;
    private AgeGroup ageGroup;
    private ApplicationAreaGroup applicationAreaGroup;
    @NotBlank
    private String brand;
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
    private MaterialBehavior materialBehavior;
    @Valid
    private MaterialParameter materialParameter;
    private Motif motif;
    private Neurodermatitis neurodermatitis;
    private OekotexStandard oekotexStandard;
    private PriceRange priceRange;
    private Rating rating;
    @NotEmpty
    private List<Size> sizes;
    private List<SpecificBodyPart> specificBodyParts;
    private List<SpecificFunctionality> specificFunctionalities;
    private Sustainability sustainability;

    public static String[] getCSVHeaders() {
        return new String[]{ProductCSVHeader.TITLE.value(),
                ProductCSVHeader.DESCRIPTION.value(),
                ProductCSVHeader.PRICE_AMOUNT.value(),
                ProductCSVHeader.PRICE_CURRENCY.value(),
                ProductCSVHeader.IMAGE_URL.value(),
                ProductCSVHeader.AGE_GROUP_ADULT_AGE_GROUP.value(),
                ProductCSVHeader.AGE_GROUP_CHILDREN_AGE_GROUP.value(),
                ProductCSVHeader.APPLICATION_AREA_GROUP_APPLICATION_AREA.value(),
                ProductCSVHeader.APPLICATION_AREA_GROUP_USAGE_CYCLE.value(),
                ProductCSVHeader.BRAND.value(),
                ProductCSVHeader.CATEGORY.value(),
                ProductCSVHeader.CERTIFICATIONS.value(),
                ProductCSVHeader.COLOR.value(),
                ProductCSVHeader.COMPOSITION_ACTIVE_SUBSTANCE_AREAS.value(),
                ProductCSVHeader.COMPOSITION_ACTIVE_SUBSTANCES.value(),
                ProductCSVHeader.COMPOSITION_ACTIVE_SUBSTANCE_PLACEMENTS.value(),
                ProductCSVHeader.COMPOSITION_ACTIVE_SUBSTANCE_RELEASES.value(),
                ProductCSVHeader.COMPOSITION_COMPOSITIONS.value(),
                ProductCSVHeader.COMPOSITION_STAGGERINGS.value(),
                ProductCSVHeader.DESIGN_DESIGN_APPEARANCE.value(),
                ProductCSVHeader.DESIGN_DESIGN_COLOR.value(),
                ProductCSVHeader.DESIGN_BODY_PARTS.value(),
                ProductCSVHeader.FIBER_TYPES.value(),
                ProductCSVHeader.GENDER.value(),
                ProductCSVHeader.HAPTICS_ELASTICITY.value(),
                ProductCSVHeader.HAPTICS_FINENESS.value(),
                ProductCSVHeader.HAPTICS_LIGHTWEIGHT.value(),
                ProductCSVHeader.HAPTICS_LINT_FREE.value(),
                ProductCSVHeader.HAPTICS_SCRATCHY.value(),
                ProductCSVHeader.HAPTICS_SEAM_FEELABLE.value(),
                ProductCSVHeader.HAPTICS_SOFTNESS.value(),
                ProductCSVHeader.HAPTICS_UNIFORM.value(),
                ProductCSVHeader.MATERIAL_BEHAVIOR_ABRASSION_RESISTANT.value(),
                ProductCSVHeader.MATERIAL_BEHAVIOR_ABSORBENCY.value(),
                ProductCSVHeader.MATERIAL_BEHAVIOR_ANTISTATIC.value(),
                ProductCSVHeader.MATERIAL_BEHAVIOR_BREATHABLE.value(),
                ProductCSVHeader.MATERIAL_BEHAVIOR_COLORFAST.value(),
                ProductCSVHeader.MATERIAL_BEHAVIOR_MOISTURE_TRANSPORTING.value(),
                ProductCSVHeader.MATERIAL_BEHAVIOR_ODOR_NEUTRALIZING.value(),
                ProductCSVHeader.MATERIAL_BEHAVIOR_SCRATCH_RESISTANT.value(),
                ProductCSVHeader.MATERIAL_BEHAVIOR_SWEAT_WICKING.value(),
                ProductCSVHeader.MATERIAL_BEHAVIOR_WASHABLE.value(),
                ProductCSVHeader.MATERIAL_PARAMETER_THICKNESS.value(),
                ProductCSVHeader.MATERIAL_PARAMETER_WEIGHT_PER_UNIT_AREA.value(),
                ProductCSVHeader.MATERIAL_PARAMETER_BREATHABILITY.value(),
                ProductCSVHeader.MATERIAL_PARAMETER_MOISTURE_WICKING.value(),
                ProductCSVHeader.MOTIF.value(),
                ProductCSVHeader.NEURODERMATITIS.value(),
                ProductCSVHeader.OEKOTEXSTANDARD.value(),
                ProductCSVHeader.PRICE_RANGE.value(),
                ProductCSVHeader.RATING.value(),
                ProductCSVHeader.SIZES.value(),
                ProductCSVHeader.SPECIFIC_BODY_PARTS.value(),
                ProductCSVHeader.SPECIFIC_FUNCTIONALITIES.value(),
                ProductCSVHeader.SUSTAINABILITY_ENVIRONMENTAL_COMPATIBILITIES.value(),
                ProductCSVHeader.SUSTAINABILITY_LIFE_CYCLES.value(),
                ProductCSVHeader.SUSTAINABILITY_REGIONALITY_LIST.value(),
                ProductCSVHeader.SUSTAINABILITY_RESOURCE_CONSUMPTIONS.value(),
                ProductCSVHeader.SUSTAINABILITY_SOCIAL_ETHICS.value(),
                ProductCSVHeader.SUSTAINABILITY_SUSTAINABILITY_COMPOSITIONS.value(),
                ProductCSVHeader.SUSTAINABILITY_SUSTAINABILITY_LIGHTWEIGHTS.value(),
                ProductCSVHeader.SKILLS.value(),
        };
    }
}
