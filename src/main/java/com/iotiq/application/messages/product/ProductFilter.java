package com.iotiq.application.messages.product;

import com.iotiq.application.entity.product.Product;
import com.iotiq.application.entity.product.productFilters.Certification;
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
import com.iotiq.commons.message.request.PageableRequest;
import com.iotiq.commons.message.request.SearchRequest;
import jakarta.persistence.criteria.Join;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductFilter extends PageableRequest implements SearchRequest<Product> {

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

    @Override
    public Specification<Product> buildSpecification(){

        return Specification.where(isLike("title", search))
                .and(isLike("price", price))
                .and(isIn("ageGroup", "adultEnum", adultEnum))
                .and(isIn("ageGroup", "childrenEnum", childrenEnum))
                .and(isIn("applicationArea","applicationAreaEnum", applicationAreaEnum))
                .and(isIn("applicationArea","frequencyEnum", frequencyEnum))
                .and(composition == null ?  null : isInList("composition","activeSubstanceAreaEnum", composition.getActiveSubstanceAreaEnum()))
                .and(composition == null ?  null : isInList("composition","activeSubstanceEnum", composition.getActiveSubstanceEnum()))
                .and(composition == null ?  null : isInList("composition","activeSubstancePlacementEnum", composition.getActiveSubstancePlacementEnum()))
                .and(composition == null ?  null : isInList("composition","activeSubstanceReleaseEnum", composition.getActiveSubstanceReleaseEnum()))
                .and(composition == null ?  null : isInList("composition","compositionEnum", composition.getCompositionEnum()))
                .and(composition == null ?  null : isInList("composition","staggeringEnum", composition.getStaggeringEnum()))
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
                .and(sustainability == null ?  null : isInList("sustainability","environmentalCompatibilityEnum", sustainability.getEnvironmentalCompatibilityEnum()))
                .and(sustainability == null ?  null : isInList("sustainability","lifeCycleEnum", sustainability.getLifeCycleEnum()))
                .and(sustainability == null ?  null : isInList("sustainability","regionalityEnum", sustainability.getRegionalityEnum()))
                .and(sustainability == null ?  null : isInList("sustainability","resourceConsumptionEnum", sustainability.getResourceConsumptionEnum()))
                .and(sustainability == null ?  null : isInList("sustainability","socialEthicsEnum", sustainability.getSocialEthicsEnum()))
                .and(sustainability == null ?  null : isInList("sustainability","sustainabilityCompositionEnum", sustainability.getSustainabilityCompositionEnum()))
                .and(sustainability == null ?  null : isInList("sustainability","sustainabilityLightweightEnum", sustainability.getSustainabilityLightweightEnum()))
                .and(isIn("brand","brandEnum", brands))
                .and(isIn("category","categoryEnum", categories))
                .and(isInList("certification","certificationEnum", certifications))
                .and(isIn("color","colorEnum", colors))
                .and(isInList("designBodyPart","designBodyPartEnum", designBodyParts))
                .and(isInList("fiber","fiberEnum", fibers))
                .and(isIn("gender","genderEnum",genders))
                .and(isIn("material","materialEnum", materials))
                .and(isIn("materialParameter","materialParameterEnum", materialParameters))
                .and(isIn("motif","motifEnum", motifs))
                .and(isIn("neurodermatitis","neurodermatitisEnum", neurodermatitis))
                .and(isIn("oekotexStandard","oekotexStandardEnum", oekotexStandards))
                .and(isIn("priceRange","priceRangeEnum", priceRanges))
                .and(isIn("rating","ratingEnum", ratings))
                .and(isIn("size","sizeEnum", sizes))
                .and(isInList("specificBodyPart","specificBodyPartEnum", specificBodyParts))
                .and(isInList("specificFunctionality","specificFunctionalityEnum", specificFunctionalities));

    }

    private Specification<Product> isLike(String attribute, String value) {
        return (root, query, cb) -> value == null ? null : cb.like(cb.lower(root.get(attribute)), "%" + value.toLowerCase() + "%");
    }

    private <E extends Enum<E>> Specification<Product> isIn(String attribute, String subAttribute, E values) {
        return (root, query, cb) -> values == null ? null : root.get(attribute).get(subAttribute).in(values.name());
    }

    private <E extends Enum<E>> Specification<Product> isIn(String attribute, String subAttribute, List<E> values) {
        return (root, query, cb) -> values == null ? null : root.get(attribute).get(subAttribute).in(values);
    }

    private <E extends Enum<E>> Specification<Product> isInList(String attribute, String subAttribute, List<E> values) {
        return (root, query, cb) -> {
            if(values == null) {
                return cb.conjunction();
            }
            Join<Product, Certification> join = root.join(attribute);
            Join<Certification, CertificationEnum> enumJoin = join.join(subAttribute);
            return enumJoin.in(values);
        };
    }
}
