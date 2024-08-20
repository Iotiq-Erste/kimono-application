package com.iotiq.application.messages.product;

import com.iotiq.application.entity.product.Product;
import com.iotiq.application.entity.product.productFilters.AgeGroup;
import com.iotiq.application.entity.product.productFilters.ApplicationArea;
import com.iotiq.application.entity.product.productFilters.Composition;
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
import com.iotiq.application.entity.product.productFilters.productEnums.sustainabilityEnums.EnvironmentalCompatibilityEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.sustainabilityEnums.LifeCycleEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.sustainabilityEnums.RegionalityEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.sustainabilityEnums.ResourceConsumptionEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.sustainabilityEnums.SocialEthicsEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.sustainabilityEnums.SustainabilityCompositionEnum;
import com.iotiq.application.entity.product.productFilters.productEnums.sustainabilityEnums.SustainabilityLightweightEnum;
import com.iotiq.commons.message.request.PageableRequest;
import com.iotiq.commons.message.request.SearchRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductFilter extends PageableRequest implements SearchRequest<Product> {

    private String search;

    private String title;
    private String description;
    private String price;

    private AgeGroup ageGroup;

    private ApplicationArea applicationArea;

    private Composition composition;

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
    private ColorEnum color;
    private DesignBodyPartEnum designBodyParts;
    private FiberEnum fibers;
    private GenderEnum genders;
    private MaterialEnum materials;
    private MaterialParameterEnum materialParameters;
    private MotifEnum motifs;
    private NeurdermatitisEnum neurodermatitis;
    private OekotexStandardEnum oekotexStandards;
    private PriceRangeEnum priceRanges;
    private RatingEnum ratings;
    private SizeEnum sizes;
    private SpecificBodyPartEnum specificBodyParts;
    private SpecificFunctionalityEnum specificFunctionalities;

    @Override
    public Specification<Product> buildSpecification(){

       return Specification.where(isLike("title", search))
                .and(isLike("price", price))
                .and(ageGroup == null ?  null : isIn("ageGroup", "adultEnum", ageGroup.getAdultEnum()))
                .and(ageGroup == null ?  null : isIn("ageGroup", "childrenEnum", ageGroup.getChildrenEnum()))
                .and(applicationArea == null ?  null : isIn("applicationArea","applicationAreaEnum", applicationArea.getApplicationAreaEnum()))
                .and(applicationArea == null ?  null : isIn("applicationArea","frequencyEnum", applicationArea.getFrequencyEnum()))
                .and(composition == null ?  null : isIn("composition","activeSubstanceAreaEnum", composition.getActiveSubstanceAreaEnum()))
                .and(composition == null ?  null : isIn("composition","activeSubstanceEnum", composition.getActiveSubstanceEnum()))
                .and(composition == null ?  null : isIn("composition","activeSubstancePlacementEnum", composition.getActiveSubstancePlacementEnum()))
                .and(composition == null ?  null : isIn("composition","activeSubstanceReleaseEnum", composition.getActiveSubstanceReleaseEnum()))
                .and(composition == null ?  null : isIn("composition","compositionEnum", composition.getCompositionEnum()))
                .and(composition == null ?  null : isIn("composition","staggeringEnum", composition.getStaggeringEnum()))
                .and(isIn("design","designAppearanceEnum", designAppearances))
                .and(isIn("design","designColorEnum", designColors))
                .and(isIn("haptics","elasticEnum", elastics))
                .and(isIn("haptics","fineEnum", fines))
                .and(isIn("haptics","lightweightEnum", lightweights))
                .and(isIn("haptics","lintFreeEnum", lintFrees))
                .and(isIn("haptics","scratchyEnum", schratchies))
                .and(isIn("haptics","seamFeelableEnum", seamFeelables))
                .and(isIn("haptics","softEnum", softs))
                .and(isIn("haptics","uniformEnum", uniforms))
                .and(isIn("materialBehavior","abrassionResistantEnum", abrassionResistants))
                .and(isIn("materialBehavior","absorbentEnum", absorbents))
                .and(isIn("materialBehavior","antistaticEnum", antistatics))
                .and(isIn("materialBehavior","breathableEnum", breathables))
                .and(isIn("materialBehavior","colorfastEnum", colorfasts))
                .and(isIn("materialBehavior","moistureTransportingEnum", moistureTransportings))
                .and(isIn("materialBehavior","odorNeutralizingEnum", odorNeutralizings))
                .and(isIn("materialBehavior","scratchResistantEnum", scratchResistants))
                .and(isIn("materialBehavior","sweatWickingEnum", sweatWickings))
                .and(isIn("materialBehavior","washableEnum", washables))
                .and(isIn("sustainability","environmentalCompatibilityEnum", environmentalCompatibilities))
                .and(isIn("sustainability","lifeCycleEnum", lifeCycles))
                .and(isIn("sustainability","regionalityEnum", regionalities))
                .and(isIn("sustainability","resourceConsumptionEnum", resourceConsumptions))
                .and(isIn("sustainability","socialEthicsEnum", socialEthics))
                .and(isIn("sustainability","sustainabilityCompositionEnum", sustainabilityCompositions))
                .and(isIn("sustainability","sustainabilityLightweightEnum", sustainabilityLightweights))
                .and(isIn("brand","brandEnum", brands))
                .and(isIn("category","categoryEnum", categories))
                .and(isIn("certification","certificationEnum", certifications))
                .and(isIn("color","colorEnum", color))
                .and(isIn("designBodyPart","designBodyPartEnum", designBodyParts))
                .and(isIn("fiber","fiberEnum", fibers))
                .and(isIn("gender","genderEnum",genders))
                .and(isIn("material","materialEnum", materials))
                .and(isIn("materialParameter","materialParameterEnum", materialParameters))
                .and(isIn("motif","motifEnum", motifs))
                .and(isIn("neurodermatitis","neurodermatitisEnum", neurodermatitis))
                .and(isIn("oekotexStandard","oekotexStandardEnum", oekotexStandards))
                .and(isIn("priceRange","priceRangeEnum", priceRanges))
                .and(isIn("rating","ratingEnum", ratings))
                .and(isIn("size","sizeEnum", sizes))
                .and(isIn("specificBodyPart","specificBodyPartEnum", specificBodyParts))
                .and(isIn("specificFunctionality","specificFunctionalityEnum", specificFunctionalities));

    }

    private Specification<Product> isLike(String attribute, String value) {
        return (root, query, cb) -> value == null ? null : cb.like(cb.lower(root.get(attribute)), "%" + value.toLowerCase() + "%");
    }

    private Specification<Product> isLike(String attribute,String subAttribute, String value) {
        return (root, query, cb) -> value == null ? null : cb.like(cb.lower(root.get(attribute).get(subAttribute)), "%" + value.toLowerCase() + "%");
    }

    private <E extends Enum<E>> Specification<Product> isIn(String attribute, String subAttribute, E values) {
        return (root, query, cb) -> values == null ? null : root.get(attribute).get(subAttribute).in(values.name());
    }

    private <E extends Enum<E>> Specification<Product> isIn(String attribute, String subAttribute, Set<E> values) {
        return (root, query, cb) -> values == null ? null : root.get(attribute).get(subAttribute).in(values);
    }
}
