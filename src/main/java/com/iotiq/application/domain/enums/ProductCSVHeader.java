package com.iotiq.application.domain.enums;

public enum ProductCSVHeader {

    TITLE("title"),
    DESCRIPTION("description"),
    PRICE_AMOUNT("price.amount"),
    PRICE_CURRENCY("price.currency"),
    IMAGE_URL("imageUrl"),
    AGE_GROUP_ADULT_AGE_GROUP("ageGroup.adultAgeGroup"),
    AGE_GROUP_CHILDREN_AGE_GROUP("ageGroup.childrenAgeGroup"),
    APPLICATION_AREA_GROUP_APPLICATION_AREA("applicationAreaGroup.applicationArea"),
    APPLICATION_AREA_GROUP_USAGE_CYCLE("applicationAreaGroup.usageCycle"),
    BRAND("brand"),
    CATEGORY("category"),
    CERTIFICATIONS("certifications"),
    COLOR("color"),
    COMPOSITION_ACTIVE_SUBSTANCE_AREAS("composition.activeSubstanceAreas"),
    COMPOSITION_ACTIVE_SUBSTANCES("composition.activeSubstances"),
    COMPOSITION_ACTIVE_SUBSTANCE_PLACEMENTS("composition.activeSubstancePlacements"),
    COMPOSITION_ACTIVE_SUBSTANCE_RELEASES("composition.activeSubstanceReleases"),
    COMPOSITION_COMPOSITIONS("composition.compositions"),
    COMPOSITION_STAGGERINGS("composition.staggerings"),
    DESIGN_DESIGN_APPEARANCE("design.designAppearance"),
    DESIGN_DESIGN_COLOR("design.designColor"),
    DESIGN_BODY_PARTS("designBodyParts"),
    FIBER_TYPES("fiberTypes"),
    GENDER("gender"),
    HAPTICS_ELASTICITY("haptics.elasticity"),
    HAPTICS_FINENESS("haptics.fineness"),
    HAPTICS_LIGHTWEIGHT("haptics.lightweight"),
    HAPTICS_LINT_FREE("haptics.lintFree"),
    HAPTICS_SCRATCHY("haptics.scratchy"),
    HAPTICS_SEAM_FEELABLE("haptics.seamFeelable"),
    HAPTICS_SOFTNESS("haptics.softness"),
    HAPTICS_UNIFORM("haptics.uniform"),
    MATERIAL_BEHAVIOR_ABRASSION_RESISTANT("materialBehavior.abrassionResistant"),
    MATERIAL_BEHAVIOR_ABSORBENCY("materialBehavior.absorbency"),
    MATERIAL_BEHAVIOR_ANTISTATIC("materialBehavior.antistatic"),
    MATERIAL_BEHAVIOR_BREATHABLE("materialBehavior.breathable"),
    MATERIAL_BEHAVIOR_COLORFAST("materialBehavior.colorfast"),
    MATERIAL_BEHAVIOR_MOISTURE_TRANSPORTING("materialBehavior.moistureTransporting"),
    MATERIAL_BEHAVIOR_ODOR_NEUTRALIZING("materialBehavior.odorNeutralizing"),
    MATERIAL_BEHAVIOR_SCRATCH_RESISTANT("materialBehavior.scratchResistant"),
    MATERIAL_BEHAVIOR_SWEAT_WICKING("materialBehavior.sweatWicking"),
    MATERIAL_BEHAVIOR_WASHABLE("materialBehavior.washable"),
    MATERIAL_PARAMETER_THICKNESS("materialParameter.thickness"),
    MATERIAL_PARAMETER_FLEXIBILITY("materialParameter.flexibility"),
    MATERIAL_PARAMETER_BREATHABILITY("materialParameter.breathability"),
    MATERIAL_PARAMETER_MOISTURE_WICKING("materialParameter.moistureWicking"),
    MOTIF("motif"),
    NEURODERMATITIS("neurodermatitis"),
    OEKOTEXSTANDARD("oekotexStandard"),
    PRICE_RANGE("priceRange"),
    RATING("rating"),
    SIZES("sizes"),
    SPECIFIC_BODY_PARTS("specificBodyParts"),
    SPECIFIC_FUNCTIONALITIES("specificFunctionalities"),
    SUSTAINABILITY_ENVIRONMENTAL_COMPATIBILITIES("sustainability.environmentalCompatibilities"),
    SUSTAINABILITY_LIFE_CYCLES("sustainability.lifeCycles"),
    SUSTAINABILITY_REGIONALITY_LIST("sustainability.regionalityList"),
    SUSTAINABILITY_RESOURCE_CONSUMPTIONS("sustainability.resourceConsumptions"),
    SUSTAINABILITY_SOCIAL_ETHICS("sustainability.socialEthics"),
    SUSTAINABILITY_SUSTAINABILITY_COMPOSITIONS("sustainability.sustainabilityCompositions"),
    SUSTAINABILITY_SUSTAINABILITY_LIGHTWEIGHTS("sustainability.sustainabilityLightweights"),
    SKILLS("skills");

    private final String header;

    ProductCSVHeader(String header) {
        this.header = header;
    }

    public String value() {
        return header;
    }
}
