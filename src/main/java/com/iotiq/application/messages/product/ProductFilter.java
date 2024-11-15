package com.iotiq.application.messages.product;

import com.iotiq.application.domain.AgeGroup_;
import com.iotiq.application.domain.ApplicationAreaGroup_;
import com.iotiq.application.domain.Composition_;
import com.iotiq.application.domain.Design_;
import com.iotiq.application.domain.Haptics_;
import com.iotiq.application.domain.MaterialBehavior_;
import com.iotiq.application.domain.MaterialParameter_;
import com.iotiq.application.domain.Price_;
import com.iotiq.application.domain.Product;
import com.iotiq.application.domain.Product_;
import com.iotiq.application.domain.Sustainability_;
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
    private BigDecimal lowestWeightPerUnitArea;
    private BigDecimal highestWeightPerUnitArea;
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
                .where(isLike(Product_.TITLE, search))
                .and(findInRange(Product_.PRICE, Price_.AMOUNT, lowestPrice, highestPrice))
                .and(isIn(Product_.AGE_GROUP, AgeGroup_.ADULT_AGE_GROUP, adultAgeGroups))
                .and(isIn(Product_.AGE_GROUP, AgeGroup_.CHILDREN_AGE_GROUP, childrenAgeGroups))
                .and(isIn(Product_.APPLICATION_AREA_GROUP, ApplicationAreaGroup_.APPLICATION_AREA, applicationAreas))
                .and(isIn(Product_.APPLICATION_AREA_GROUP, ApplicationAreaGroup_.USAGE_CYCLE, usageCycles))
                .and(isInList(Product_.COMPOSITION, Composition_.ACTIVE_SUBSTANCE_AREAS, activeSubstanceAreas))
                .and(isInList(Product_.COMPOSITION, Composition_.ACTIVE_SUBSTANCES, activeSubstances))
                .and(isInList(Product_.COMPOSITION, Composition_.ACTIVE_SUBSTANCE_PLACEMENTS, activeSubstancePlacements))
                .and(isInList(Product_.COMPOSITION, Composition_.ACTIVE_SUBSTANCE_RELEASES, activeSubstanceReleases))
                .and(isInList(Product_.COMPOSITION, Composition_.COMPOSITIONS, compositions))
                .and(isInList(Product_.COMPOSITION, Composition_.STAGGERINGS, staggerings))
                .and(isIn(Product_.DESIGN, Design_.DESIGN_APPEARANCE, designAppearances))
                .and(isIn(Product_.DESIGN, Design_.DESIGN_COLOR, designColors))
                .and(isIn(Product_.HAPTICS, Haptics_.ELASTICITY, elasticities))
                .and(isIn(Product_.HAPTICS, Haptics_.FINENESS, finenesses))
                .and(isIn(Product_.HAPTICS, Haptics_.LIGHTWEIGHT, lightweights))
                .and(isIn(Product_.HAPTICS, Haptics_.LINT_FREE, lintFrees))
                .and(isIn(Product_.HAPTICS, Haptics_.SCRATCHY, schratchies))
                .and(isIn(Product_.HAPTICS, Haptics_.SEAM_FEELABLE, seamFeelables))
                .and(isIn(Product_.HAPTICS, Haptics_.SOFTNESS, softnesses))
                .and(isIn(Product_.HAPTICS, Haptics_.UNIFORM, uniforms))
                .and(isIn(Product_.MATERIAL_BEHAVIOR, MaterialBehavior_.ABRASION_RESISTANT, abrassionResistants))
                .and(isIn(Product_.MATERIAL_BEHAVIOR, MaterialBehavior_.ABSORBENCY, absorbencies))
                .and(isIn(Product_.MATERIAL_BEHAVIOR, MaterialBehavior_.ANTISTATIC, antistatics))
                .and(isIn(Product_.MATERIAL_BEHAVIOR, MaterialBehavior_.BREATHABLE, breathables))
                .and(isIn(Product_.MATERIAL_BEHAVIOR, MaterialBehavior_.COLORFAST, colorfasts))
                .and(isIn(Product_.MATERIAL_BEHAVIOR, MaterialBehavior_.MOISTURE_TRANSPORTING, moistureTransportings))
                .and(isIn(Product_.MATERIAL_BEHAVIOR, MaterialBehavior_.ODOR_NEUTRALIZING, odorNeutralizings))
                .and(isIn(Product_.MATERIAL_BEHAVIOR, MaterialBehavior_.SCRATCH_RESISTANT, scratchResistants))
                .and(isIn(Product_.MATERIAL_BEHAVIOR, MaterialBehavior_.SWEAT_WICKING, sweatWickings))
                .and(isIn(Product_.MATERIAL_BEHAVIOR, MaterialBehavior_.WASHABLE, washables))
                .and(isInList(Product_.SUSTAINABILITY, Sustainability_.ENVIRONMENTAL_COMPATIBILITIES, environmentalCompatibilities))
                .and(isInList(Product_.SUSTAINABILITY, Sustainability_.LIFE_CYCLES, lifeCycles))
                .and(isInList(Product_.SUSTAINABILITY, Sustainability_.REGIONALITY_LIST, regionalityList))
                .and(isInList(Product_.SUSTAINABILITY, Sustainability_.RESOURCE_CONSUMPTIONS, resourceConsumptions))
                .and(isInList(Product_.SUSTAINABILITY, Sustainability_.SOCIAL_ETHICS, socialEthics))
                .and(isInList(Product_.SUSTAINABILITY, Sustainability_.SUSTAINABILITY_COMPOSITIONS, sustainabilityCompositions))
                .and(isInList(Product_.SUSTAINABILITY, Sustainability_.SUSTAINABILITY_LIGHTWEIGHTS, sustainabilityLightweights))
                .and(isInList(Product_.SUSTAINABILITY, Sustainability_.SKILLS, skills))
                .and(listIn(Product_.BRAND, brands))
                .and(isIn(Product_.CATEGORY, categories))
                .and(isInList(Product_.CERTIFICATIONS, certifications))
                .and(isIn(Product_.COLOR, colors))
                .and(isInList(Product_.DESIGN_BODY_PARTS, designBodyParts))
                .and(isInList(Product_.FIBER_TYPES, fiberTypes))
                .and(isIn(Product_.GENDER, genders))
                .and(findInRange(Product_.MATERIAL_PARAMETER, MaterialParameter_.THICKNESS, lowestThickness, highestThickness))
                .and(findInRange(Product_.MATERIAL_PARAMETER, MaterialParameter_.WEIGHT_PER_UNIT_AREA, (lowestWeightPerUnitArea != null) ? lowestWeightPerUnitArea : null, (highestWeightPerUnitArea != null) ? highestWeightPerUnitArea : null))
                .and(findInRange(Product_.MATERIAL_PARAMETER, MaterialParameter_.BREATHABILITY, (lowestBreathability != null) ? BigDecimal.valueOf(lowestBreathability) : null, (highestBreathability != null) ? BigDecimal.valueOf(highestBreathability) : null))
                .and(findInRange(Product_.MATERIAL_PARAMETER, MaterialParameter_.MOISTURE_WICKING, (lowestMoistureWicking != null) ? BigDecimal.valueOf(lowestMoistureWicking) : null, (highestMoistureWicking != null) ? BigDecimal.valueOf(highestMoistureWicking) : null))
                .and(isIn(Product_.MOTIF, motifs))
                .and(isIn(Product_.NEURODERMATITIS, neurodermatitis))
                .and(isIn(Product_.OEKOTEX_STANDARD, oekotexStandards))
                .and(isIn(Product_.PRICE_RANGE, priceRanges))
                .and(isIn(Product_.RATING, ratings))
                .and(isInList(Product_.SIZES, sizes))
                .and(isInList(Product_.SPECIFIC_BODY_PARTS, specificBodyParts))
                .and(isInList(Product_.SPECIFIC_FUNCTIONALITIES, specificFunctionalities));

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
