package com.iotiq.application.messages.product;

import com.iotiq.application.domain.Product;
import com.iotiq.application.domain.Product_;
import com.iotiq.application.domain.enums.AbrassionResistant;
import com.iotiq.application.domain.enums.Absorbency;
import com.iotiq.application.domain.enums.ActiveSubstance;
import com.iotiq.application.domain.enums.ActiveSubstanceArea;
import com.iotiq.application.domain.enums.ActiveSubstancePlacement;
import com.iotiq.application.domain.enums.ActiveSubstanceRelease;
import com.iotiq.application.domain.enums.AdultAgeGroup;
import com.iotiq.application.domain.enums.Antistatic;
import com.iotiq.application.domain.enums.ApplicationArea;
import com.iotiq.application.domain.enums.Breathable;
import com.iotiq.application.domain.enums.Category;
import com.iotiq.application.domain.enums.Certification;
import com.iotiq.application.domain.enums.ChildrenAgeGroup;
import com.iotiq.application.domain.enums.Color;
import com.iotiq.application.domain.enums.Colorfast;
import com.iotiq.application.domain.enums.Currency;
import com.iotiq.application.domain.enums.DesignAppearance;
import com.iotiq.application.domain.enums.DesignBodyPart;
import com.iotiq.application.domain.enums.DesignColor;
import com.iotiq.application.domain.enums.Elasticity;
import com.iotiq.application.domain.enums.EnvironmentalCompatibility;
import com.iotiq.application.domain.enums.FiberType;
import com.iotiq.application.domain.enums.Fineness;
import com.iotiq.application.domain.enums.Gender;
import com.iotiq.application.domain.enums.LifeCycle;
import com.iotiq.application.domain.enums.Lightweight;
import com.iotiq.application.domain.enums.LintFree;
import com.iotiq.application.domain.enums.MoistureTransporting;
import com.iotiq.application.domain.enums.Motif;
import com.iotiq.application.domain.enums.Neurodermatitis;
import com.iotiq.application.domain.enums.OdorNeutralizing;
import com.iotiq.application.domain.enums.OekotexStandard;
import com.iotiq.application.domain.enums.PriceRange;
import com.iotiq.application.domain.enums.Rating;
import com.iotiq.application.domain.enums.Regionality;
import com.iotiq.application.domain.enums.ResourceConsumption;
import com.iotiq.application.domain.enums.ScratchResistant;
import com.iotiq.application.domain.enums.Scratchy;
import com.iotiq.application.domain.enums.SeamFeelable;
import com.iotiq.application.domain.enums.Size;
import com.iotiq.application.domain.enums.Skill;
import com.iotiq.application.domain.enums.SocialEthics;
import com.iotiq.application.domain.enums.Softness;
import com.iotiq.application.domain.enums.SpecificBodyPart;
import com.iotiq.application.domain.enums.SpecificFunctionality;
import com.iotiq.application.domain.enums.Staggering;
import com.iotiq.application.domain.enums.SustainabilityComposition;
import com.iotiq.application.domain.enums.SustainabilityLightweight;
import com.iotiq.application.domain.enums.SweatWicking;
import com.iotiq.application.domain.enums.Uniform;
import com.iotiq.application.domain.enums.UsageCycle;
import com.iotiq.application.domain.enums.Washable;
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

import static com.iotiq.application.service.ProductSpecification.findInRange;
import static com.iotiq.application.service.ProductSpecification.isIn;
import static com.iotiq.application.service.ProductSpecification.isInList;
import static com.iotiq.application.service.ProductSpecification.isLike;
import static com.iotiq.application.service.ProductSpecification.listIn;


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

    private List<UsageCycle> usageCycles;

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

    private List<String> brands;
    private List<Category> categories;
    private List<Certification> certifications;
    private List<Color> colors;
    private List<DesignBodyPart> designBodyParts;
    private List<FiberType> fiberTypes;
    private List<Gender> genders;

    // MaterialParameter
    private BigDecimal lowestThickness;
    private BigDecimal highestThickness;
    private Integer lowestFlexibility;
    private Integer highestFlexibility;
    private Integer lowestBreathability;
    private Integer highestBreathability;
    private Integer lowestMoistureWicking;
    private Integer highestMoistureWicking;

    private List<Motif> motifs;
    private List<Neurodermatitis> neurodermatitis;
    private List<OekotexStandard> oekotexStandards;
    private List<PriceRange> priceRanges;
    private List<Rating> ratings;
    private List<Size> sizes;
    private List<SpecificBodyPart> specificBodyParts;
    private List<SpecificFunctionality> specificFunctionalities;
    private List<Skill> skills;

    @Override
    public Specification<Product> buildSpecification() {
        Specification<Product> specification = Specification
                .where(isLike("title", search))
                .and(findInRange("price", "amount", lowestPrice, highestPrice))
                .and(isIn("ageGroup", "adultAgeGroup", adultAgeGroups))
                .and(isIn("ageGroup", "childrenAgeGroup", childrenAgeGroups))
                .and(isIn("applicationAreaGroup", "applicationArea", applicationAreas))
                .and(isIn("applicationAreaGroup", "usageCycle", usageCycles))
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
                .and(listIn("brand", brands))
                .and(isIn("category", categories))
                .and(isInList("certifications", certifications))
                .and(isIn("color", colors))
                .and(isInList("designBodyParts", designBodyParts))
                .and(isInList("fiberTypes", fiberTypes))
                .and(isIn("gender", genders))
                .and(findInRange("materialParameter", "thickness", lowestThickness, highestThickness))
                .and(findInRange("materialParameter", "flexibility", (lowestFlexibility != null) ? BigDecimal.valueOf(lowestFlexibility) : null, (highestFlexibility != null) ? BigDecimal.valueOf(highestFlexibility) : null))
                .and(findInRange("materialParameter", "breathability", (lowestBreathability != null) ? BigDecimal.valueOf(lowestBreathability) : null, (highestBreathability != null) ? BigDecimal.valueOf(highestBreathability) : null))
                .and(findInRange("materialParameter", "moistureWicking", (lowestMoistureWicking != null) ? BigDecimal.valueOf(lowestMoistureWicking) : null, (highestMoistureWicking != null) ? BigDecimal.valueOf(highestMoistureWicking) : null))
                .and(isIn("motif", motifs))
                .and(isIn("neurodermatitis", neurodermatitis))
                .and(isIn("oekotexStandard", oekotexStandards))
                .and(isIn("priceRange", priceRanges))
                .and(isIn("rating", ratings))
                .and(isInList("sizes", sizes))
                .and(isInList("specificBodyParts", specificBodyParts))
                .and(isInList("specificFunctionalities", specificFunctionalities))
                .and(isInList("skills", skills));

        if (!CollectionUtils.isEmpty(sellerIds)) {
            specification = specification
                    .and((root, query, criteriaBuilder) ->
                            root.get(Product_.SELLER).get(AbstractPersistable_.ID).in(sellerIds));
        }

        return specification;
    }

    public ProductFilter withSellerId(UUID sellerId) {
        this.setSellerIds(List.of(sellerId));
        return this;
    }

}
