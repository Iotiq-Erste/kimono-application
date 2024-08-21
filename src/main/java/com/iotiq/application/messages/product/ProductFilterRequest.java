package com.iotiq.application.messages.product;

import com.iotiq.application.entity.product.productFilters.Composition;
import com.iotiq.application.entity.product.productFilters.Sustainability;
import com.iotiq.application.entity.product.productFilters.productEnums.BrandEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.CategoryEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.CertificationEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.ColorEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.DesignBodyPartEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.FiberEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.GenderEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.MaterialEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.MaterialParameterEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.MotifEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.NeurdermatitisEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.OekotexStandardEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.PriceRangeEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.RatingEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.SizeEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.SpecificBodyPartEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.SpecificFunctionalityEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.ageGroupEnums.AdultEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.ageGroupEnums.ChildrenEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.applicationAreaEnums.ApplicationAreaEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.applicationAreaEnums.FrequencyEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.designEnums.DesignAppearanceEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.designEnums.DesignColorEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.hapticsEnums.ElasticEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.hapticsEnums.FineEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.hapticsEnums.LightweightEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.hapticsEnums.LintFreeEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.hapticsEnums.ScratchyEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.hapticsEnums.SeamFeelableEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.hapticsEnums.SoftEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.hapticsEnums.UniformEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.materialBehaviorEnums.AbrassionResistantEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.materialBehaviorEnums.AbsorbentEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.materialBehaviorEnums.AntistaticEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.materialBehaviorEnums.BreathableEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.materialBehaviorEnums.ColorfastEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.materialBehaviorEnums.MoistureTransportingEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.materialBehaviorEnums.OdorNeutralizingEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.materialBehaviorEnums.ScratchResistantEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.materialBehaviorEnums.SweatWickingEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.materialBehaviorEnums.WashableEnum;
import lombok.Data;

import java.util.List;

@Data
public class ProductFilterRequest {

    private String search;

    private String price;

    private List<AdultEnum> adultEnum;

    private List<ChildrenEnum> childrenEnum;

    private List<ApplicationAreaEnum> applicationAreaEnum;

    private List<FrequencyEnum> frequencyEnum;

    private Composition composition;

    private List<DesignAppearanceEnum> designAppearances;
    private List<DesignColorEnum> designColors;

    private List<ElasticEnum> elastics;
    private List<FineEnum> fines;
    private List<LightweightEnum> lightweights;
    private List<LintFreeEnum> lintFrees;
    private List<SeamFeelableEnum> seamFeelables;
    private List<ScratchyEnum> schratchies;
    private List<SoftEnum> softs;
    private List<UniformEnum> uniforms;

    private List<AbrassionResistantEnum> abrassionResistants;
    private List<AbsorbentEnum> absorbents;
    private List<AntistaticEnum> antistatics;
    private List<BreathableEnum> breathables;
    private List<ColorfastEnum> colorfasts;
    private List<MoistureTransportingEnum> moistureTransportings;
    private List<OdorNeutralizingEnum> odorNeutralizings;
    private List<ScratchResistantEnum> scratchResistants;
    private List<SweatWickingEnum> sweatWickings;
    private List<WashableEnum> washables;

    private Sustainability sustainability;

    private List<BrandEnum> brands;
    private List<CategoryEnum> categories;
    private List<CertificationEnum> certifications;
    private List<ColorEnum> colors;
    private List<DesignBodyPartEnum> designBodyParts;
    private List<FiberEnum> fibers;
    private List<GenderEnum> genders;
    private List<MaterialEnum> materials;
    private List<MaterialParameterEnum> materialParameters;
    private List<MotifEnum> motifs;
    private List<NeurdermatitisEnum> neurodermatitis;
    private List<OekotexStandardEnum> oekotexStandards;
    private List<PriceRangeEnum> priceRanges;
    private List<RatingEnum> ratings;
    private List<SizeEnum> sizes;
    private List<SpecificBodyPartEnum> specificBodyParts;
    private List<SpecificFunctionalityEnum> specificFunctionalities;

    protected int page = 0;
    protected int size = 20;

}
