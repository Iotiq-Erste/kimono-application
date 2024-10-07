package com.iotiq.application.messages.product;

import com.iotiq.application.domain.Product;
import com.iotiq.application.domain.Product_;
import com.iotiq.application.domain.Seller_;
import com.iotiq.application.domain.enums.*;
import com.iotiq.commons.message.request.PageableRequest;
import com.iotiq.commons.message.request.SearchRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static com.iotiq.application.service.ProductSpecification.isIn;
import static com.iotiq.application.service.ProductSpecification.isInList;
import static com.iotiq.application.service.ProductSpecification.isLike;
import static com.iotiq.application.service.ProductSpecification.priceBetween;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductFilter extends PageableRequest implements SearchRequest<Product> {

    private List<UUID> sellerIds;
    private String search;

    private BigDecimal lowestPrice;
    private BigDecimal highestPrice;
    private Currency currency;

    private List<AdultAgeGroup> adultAgeGroups;

    private List<ChildrenAgeGroup> childrenAgeGroups;

    private List<ApplicationArea> applicationAreas;

    private List<Frequency> frequencies;

    //Composition
    private List<ActiveSubstanceArea> activeSubstanceAreas;
    private List<ActiveSubstance> activeSubstances;
    private List<ActiveSubstancePlacement> activeSubstancePlacements;
    private List<ActiveSubstanceRelease> activeSubstanceReleases;
    private List<com.iotiq.application.domain.enums.Composition> compositions;
    private List<Staggering> staggerings;

    private List<DesignAppearance> designAppearances;
    private List<DesignColor> designColors;

    private List<Elasticity> elasticities;
    private List<Fineness> finenesses;
    private List<Lightweight> lightweights;
    private List<LintFree> lintFrees;
    private List<SeamFeelable> seamFeelables;
    private List<Scratchy> schratchies;
    private List<Softness> softnesses;
    private List<Uniform> uniforms;

    private List<AbrassionResistant> abrassionResistants;
    private List<Absorbency> absorbencies;
    private List<Antistatic> antistatics;
    private List<Breathable> breathables;
    private List<Colorfast> colorfasts;
    private List<MoistureTransporting> moistureTransportings;
    private List<OdorNeutralizing> odorNeutralizings;
    private List<ScratchResistant> scratchResistants;
    private List<SweatWicking> sweatWickings;
    private List<Washable> washables;

    //Sustainability
    private List<EnvironmentalCompatibility> environmentalCompatibilities;
    private List<LifeCycle> lifeCycles;
    private List<Regionality> regionalityList;
    private List<ResourceConsumption> resourceConsumptions;
    private List<SocialEthics> socialEthics;
    private List<SustainabilityComposition> sustainabilityCompositions;
    private List<SustainabilityLightweight> sustainabilityLightweights;

    private List<Brand> brands;
    private List<Category> categories;
    private List<Certification> certifications;
    private List<Color> colors;
    private List<DesignBodyPart> designBodyParts;
    private List<FiberType> fiberTypes;
    private List<Gender> genders;
    private List<Material> materials;
    private List<MaterialParameter> materialParameters;
    private List<Motif> motifs;
    private List<Neurodermatitis> neurodermatitis;
    private List<OekotexStandard> oekotexStandards;
    private List<PriceRange> priceRanges;
    private List<Rating> ratings;
    private List<Size> sizes;
    private List<SpecificBodyPart> specificBodyParts;
    private List<SpecificFunctionality> specificFunctionalities;

    @Override
    public Specification<Product> buildSpecification() {
        Specification<Product> specification = Specification
                .where(isLike("title", search))
                .and(priceBetween(lowestPrice, highestPrice))
                .and(isIn("ageGroup", "adultAgeGroup", adultAgeGroups))
                .and(isIn("ageGroup", "childrenAgeGroup", childrenAgeGroups))
                .and(isIn("applicationAreaGroup", "applicationArea", applicationAreas))
                .and(isIn("applicationAreaGroup", "frequency", frequencies))
                .and(isInList("composition", "activeSubstanceAreas", activeSubstanceAreas))
                .and(isInList("composition", "activeSubstances", activeSubstances))
                .and(isInList("composition", "activeSubstancePlacements", activeSubstancePlacements))
                .and(isInList("composition", "activeSubstanceReleases", activeSubstanceReleases))
                .and(isInList("composition", "compositions", compositions))
                .and(isInList("composition", "staggerings", staggerings))
                .and(isIn("design", "designAppearance", designAppearances))
                .and(isIn("design", "designColor", designColors))
                .and(isIn("haptics", "elasticity", elasticities))
                .and(isIn("haptics", "fineness", finenesses))
                .and(isIn("haptics", "lightweight", lightweights))
                .and(isIn("haptics", "lintFree", lintFrees))
                .and(isIn("haptics", "scratchy", schratchies))
                .and(isIn("haptics", "seamFeelable", seamFeelables))
                .and(isIn("haptics", "softness", softnesses))
                .and(isIn("haptics", "uniform", uniforms))
                .and(isIn("materialBehavior", "abrasionResistant", abrassionResistants))
                .and(isIn("materialBehavior", "absorbency", absorbencies))
                .and(isIn("materialBehavior", "antistatic", antistatics))
                .and(isIn("materialBehavior", "breathable", breathables))
                .and(isIn("materialBehavior", "colorfast", colorfasts))
                .and(isIn("materialBehavior", "moistureTransporting", moistureTransportings))
                .and(isIn("materialBehavior", "odorNeutralizing", odorNeutralizings))
                .and(isIn("materialBehavior", "scratchResistant", scratchResistants))
                .and(isIn("materialBehavior", "sweatWicking", sweatWickings))
                .and(isIn("materialBehavior", "washable", washables))
                .and(isInList("sustainability", "environmentalCompatibilities", environmentalCompatibilities))
                .and(isInList("sustainability", "lifeCycles", lifeCycles))
                .and(isInList("sustainability", "regionalityList", regionalityList))
                .and(isInList("sustainability", "resourceConsumptions", resourceConsumptions))
                .and(isInList("sustainability", "socialEthics", socialEthics))
                .and(isInList("sustainability", "sustainabilityCompositions", sustainabilityCompositions))
                .and(isInList("sustainability", "sustainabilityLightweights", sustainabilityLightweights))
                .and(isIn("brand", brands))
                .and(isIn("category", categories))
                .and(isInList("certifications", certifications))
                .and(isIn("color", colors))
                .and(isInList("designBodyParts", designBodyParts))
                .and(isInList("fiberTypes", fiberTypes))
                .and(isIn("gender", genders))
                .and(isIn("material", materials))
                .and(isIn("materialParameter", materialParameters))
                .and(isIn("motif", motifs))
                .and(isIn("neurodermatitis", neurodermatitis))
                .and(isIn("oekotexStandard", oekotexStandards))
                .and(isIn("priceRange", priceRanges))
                .and(isIn("rating", ratings))
                .and(isInList("sizes", sizes))
                .and(isInList("specificBodyParts", specificBodyParts))
                .and(isInList("specificFunctionalities", specificFunctionalities));

        if (!CollectionUtils.isEmpty(sellerIds)) {
            specification = specification
                    .and((root, query, criteriaBuilder) ->
                            root.get(Product_.SELLER).get(AbstractPersistable_.ID).in(sellerIds));
        }

        return specification;
    }

}
