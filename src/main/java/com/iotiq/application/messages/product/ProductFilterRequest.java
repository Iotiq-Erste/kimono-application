package com.iotiq.application.messages.product;

import com.iotiq.application.entity.product.productFilters.AgeGroup;
import com.iotiq.application.entity.product.productFilters.productEnums.*;
import com.iotiq.application.entity.product.productFilters.productEnums.applicationAreaEnums.ApplicationAreaEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.applicationAreaEnums.FrequencyEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.compositionEnums.*;
import com.iotiq.application.entity.product.productFilters.productEnums.designEnums.DesignAppearanceEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.designEnums.DesignColorEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.hapticsEnums.*;
import com.iotiq.application.entity.product.productFilters.productEnums.materialBehaviorEnums.*;
import com.iotiq.application.entity.product.productFilters.productEnums.sustainabilityEnums.*;
import lombok.Data;

import java.util.Set;

@Data
public class ProductFilterRequest {

    private String search;

    private String title;
    private String description;
    private String price;

    private AgeGroup ageGroup;

    private ApplicationAreaEnum applicationAreas;
    private FrequencyEnum frequencies;

    private ActiveSubstanceAreaEnum activeSubstanceAreas;
    private ActiveSubstanceEnum activeSubstances;
    private ActiveSubstancePlacementEnum activeSubstancePlacements;
    private ActiveSubstanceReleaseEnum activeSubstanceReleases;
    private CompositionEnum compositions;
    private StaggeringEnum staggerings;

    private DesignAppearanceEnum designAppearances;
    private DesignColorEnum designColors;

    private ElasticEnum elastics;
    private FineEnum fines;
    private LightweightEnum lightweights;
    private LintFreeEnum lintFrees;
    private SeamFeelableEnum seamFeelables;
    private ScratchyEnum schratchies;
    private SoftEnum softs;
    private UniformEnum uniforms;

    private AbrassionResistantEnum abrassionResistants;
    private AbsorbentEnum absorbents;
    private AntistaticEnum antistatics;
    private BreathableEnum breathables;
    private ColorfastEnum colorfasts;
    private MoistureTransportingEnum moistureTransportings;
    private OdorNeutralizingEnum odorNeutralizings;
    private ScratchResistantEnum scratchResistants;
    private SweatWickingEnum sweatWickings;
    private WashableEnum washables;

    private EnvironmentalCompatibilityEnum environmentalCompatibilities;
    private LifeCycleEnum lifeCycles;
    private RegionalityEnum regionalities;
    private ResourceConsumptionEnum resourceConsumptions;
    private SocialEthicsEnum socialEthics;
    private SustainabilityCompositionEnum sustainabilityCompositions;
    private SustainabilityLightweightEnum sustainabilityLightweights;

    private BrandEnum brands;
    private Set<CategoryEnum> categories;
    private CertificationEnum certifications;
    private ColorEnum colors;
    private DesignBodyPartEnum designBodyParts;
    private FiberEnum fibers;
    private GenderEnum genders;
    private MaterialEnum materials;
    private MaterialParameterEnum materialParameter;
    private MotifEnum motifs;
    private NeurdermatitisEnum neurodermatiti;
    private OekotexStandardEnum oekotexStandard;
    private PriceRangeEnum priceRange;
    private RatingEnum rating;
    private SizeEnum size;
    private SpecificBodyPartEnum specificBodyPart;
    private SpecificFunctionalityEnum specificFunctionalitie;

}
