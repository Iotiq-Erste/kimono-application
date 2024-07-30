package com.iotiq.application.messages.product;

import com.iotiq.application.entity.product.Product;
import com.iotiq.application.entity.product.productFilters.productEnums.*;
import com.iotiq.application.entity.product.productFilters.productEnums.ageGroupEnums.*;
import com.iotiq.application.entity.product.productFilters.productEnums.applicationAreaEnums.*;
import com.iotiq.application.entity.product.productFilters.productEnums.compositionEnums.*;
import com.iotiq.application.entity.product.productFilters.productEnums.designEnums.*;
import com.iotiq.application.entity.product.productFilters.productEnums.hapticsEnums.*;
import com.iotiq.application.entity.product.productFilters.productEnums.materialBehaviorEnums.*;
import com.iotiq.application.entity.product.productFilters.productEnums.sustainabilityEnums.*;
import com.iotiq.commons.message.request.PageableRequest;
import com.iotiq.commons.message.request.SearchRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.Set;

public class ProductFilter extends PageableRequest implements SearchRequest<Product> {

    private String title;
    private String description;
    private String price;

    private Set<AdultEnum> adultEnums;
    private Set<ChildrenEnum> childrenEnums;

    private Set<ApplicationAreaEnum> applicationAreas;
    private Set<FrequencyEnum> frequencies;

    private Set<ActiveSubstanceAreaEnum> activeSubstanceAreas;
    private Set<ActiveSubstanceEnum> activeSubstances;
    private Set<ActiveSubstancePlacementEnum> activeSubstancePlacements;
    private Set<ActiveSubstanceReleaseEnum> activeSubstanceReleases;
    private Set<CompositionEnum> compositions;
    private Set<StaggeringEnum> staggerings;

    private Set<DesignAppearanceEnum> designAppearances;
    private Set<DesignColorEnum> designColors;

    private Set<ElasticEnum> elastics;
    private Set<FineEnum> fines;
    private Set<LightweightEnum> lightweights;
    private Set<LintFreeEnum> lintFrees;
    private Set<SeamFeelableEnum> seamFeelables;
    private Set<ScratchyEnum> schratchies;
    private Set<SoftEnum> softs;
    private Set<UniformEnum> uniforms;

    private Set<AbrassionResistantEnum> abrassionResistants;
    private Set<AbsorbentEnum> absorbents;
    private Set<AntistaticEnum> antistatics;
    private Set<BreathableEnum> breathables;
    private Set<ColorfastEnum> colorfasts;
    private Set<MoistureTransportingEnum> moistureTransportings;
    private Set<OdorNeutralizingEnum> odorNeutralizings;
    private Set<ScratchResistantEnum> scratchResistants;
    private Set<SweatWickingEnum> sweatWickings;
    private Set<WashableEnum> washables;

    private Set<EnvironmentalCompatibilityEnum> environmentalCompatibilities;
    private Set<LifeCycleEnum> lifeCycles;
    private Set<RegionalityEnum> regionalities;
    private Set<ResourceConsumptionEnum> resourceConsumptions;
    private Set<SocialEthicsEnum> socialEthics;
    private Set<SustainabilityCompositionEnum> sustainabilityCompositions;
    private Set<SustainabilityLightweightEnum> sustainabilityLightweights;

    private Set<BrandEnum> brands;
    private Set<CategoryEnum> categories;
    private Set<CertificationEnum> certifications;
    private Set<ColorEnum> colors;
    private Set<DesignBodyPartEnum> designBodyParts;
    private Set<FiberEnum> fibers;
    private Set<GenderEnum> genders;
    private Set<MaterialEnum> materials;
    private Set<MaterialParameterEnum> materialParameters;
    private Set<MotifEnum> motifs;
    private Set<NeurdermatitisEnum> neurodermatitis;
    private Set<OekotexStandardEnum> oekotexStandards;
    private Set<PriceRangeEnum> priceRanges;
    private Set<RatingEnum> ratings;
    private Set<SizeEnum> sizes;
    private Set<SpecificBodyPartEnum> specificBodyParts;
    private Set<SpecificFunctionalityEnum> specificFunctionalities;

    @Override
    public Specification<Product> buildSpecification(){
        return Specification.where(isLike("title", title))
                .and(isLike("description", description))
                .and(isLike("price", price))
                .and(isIn("ageGroupEnums.adultEnum", adultEnums))
                .and(isIn("ageGroupEnums.childrenEnum", childrenEnums))
                .and(isIn("applicationAreaEnums.applicationAreaEnum", applicationAreas))
                .and(isIn("applicationAreaEnums.frequencyEnum", frequencies))
                .and(isIn("compositionEnums.activeSubstanceAreaEnum", activeSubstanceAreas))
                .and(isIn("compositionEnums.activeSubstanceEnum", activeSubstances))
                .and(isIn("compositionEnums.activeSubstancePlacementEnum", activeSubstancePlacements))
                .and(isIn("compositionEnums.activeSubstanceReleaseEnum", activeSubstanceReleases))
                .and(isIn("compositionEnums.compositionEnum", compositions))
                .and(isIn("compositionEnums.staggeringEnum", staggerings))
                .and(isIn("designEnums.designAppearanceEnum", designAppearances))
                .and(isIn("designEnums.designColorEnum", designColors))
                .and(isIn("hapticsEnums.elasticEnum", elastics))
                .and(isIn("hapticsEnums.fineEnum", fines))
                .and(isIn("hapticsEnums.lightweightEnum", lightweights))
                .and(isIn("hapticsEnums.lintFreeEnum", lintFrees))
                .and(isIn("hapticsEnums.scratchyEnum", schratchies))
                .and(isIn("hapticsEnums.seamFeelableEnum", seamFeelables))
                .and(isIn("hapticsEnums.softEnum", softs))
                .and(isIn("hapticsEnums.uniformEnum", uniforms))
                .and(isIn("materialBehaviorEnums.abrassionResistantEnum", abrassionResistants))
                .and(isIn("materialBehaviorEnums.absorbentEnum", absorbents))
                .and(isIn("materialBehaviorEnums.antistaticEnum", antistatics))
                .and(isIn("materialBehaviorEnums.breathableEnum", breathables))
                .and(isIn("materialBehaviorEnums.colorfastEnum", colorfasts))
                .and(isIn("materialBehaviorEnums.moistureTransportingEnum", moistureTransportings))
                .and(isIn("materialBehaviorEnums.odorNeutralizingEnum", odorNeutralizings))
                .and(isIn("materialBehaviorEnums.scratchResistantEnum", scratchResistants))
                .and(isIn("materialBehaviorEnums.sweatWickingEnum", sweatWickings))
                .and(isIn("materialBehaviorEnums.washableEnum", washables))
                .and(isIn("sustainabilityEnums.environmentalCompatibilityEnum", environmentalCompatibilities))
                .and(isIn("sustainabilityEnums.lifeCycleEnum", lifeCycles))
                .and(isIn("sustainabilityEnums.regionalityEnum", regionalities))
                .and(isIn("sustainabilityEnums.resourceConsumptionEnum", resourceConsumptions))
                .and(isIn("sustainabilityEnums.socialEthicsEnum", socialEthics))
                .and(isIn("sustainabilityEnums.sustainabilityCompositionEnum", sustainabilityCompositions))
                .and(isIn("sustainabilityEnums.sustainabilityLightweightEnum", sustainabilityLightweights))
                .and(isIn("brandEnum", brands))
                .and(isIn("categoryEnum", categories))
                .and(isIn("certificationEnum", certifications))
                .and(isIn("colorEnum", colors))
                .and(isIn("designBodyPartEnum", designBodyParts))
                .and(isIn("fiberEnum", fibers))
                .and(isIn("genderEnum",genders))
                .and(isIn("materialEnum", materials))
                .and(isIn("materialParameterEnum", materialParameters))
                .and(isIn("motifEnum", motifs))
                .and(isIn("neurodermatitisEnum", neurodermatitis))
                .and(isIn("oekotexStandardEnum", oekotexStandards))
                .and(isIn("priceRangeEnum", priceRanges))
                .and(isIn("ratingEnum", ratings))
                .and(isIn("sizeEnum", sizes))
                .and(isIn("specificBodyPartEnum", specificBodyParts))
                .and(isIn("specificFunctionalityEnum", specificFunctionalities));
    }

    private Specification<Product> isLike(String attribute, String value) {
        return (root, query, cb) -> value == null ? null : cb.like(cb.lower(root.get(attribute)), "%" + value.toLowerCase() + "%");
    }

    private <E extends Enum<E>> Specification<Product> isIn(String attribute, Set<E> values) {
        return (root, query, cb) -> values == null || values.isEmpty() ? null : root.get(attribute).in(values);
    }
}
