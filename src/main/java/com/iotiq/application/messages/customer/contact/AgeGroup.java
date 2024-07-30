package com.iotiq.application.messages.customer.contact;

public enum AgeGroup {
    INFANT,
    TODDLER,
    CHILD,
    TEEN,
    YOUNG_ADULT,
    ADULT,
    SENIOR;

    @Override
    public String toString() {
        return switch (this) {
            case INFANT -> "Infant (0-1 year)";
            case TODDLER -> "Toddler (1-3 years)";
            case CHILD -> "Child (4-12 years)";
            case TEEN -> "Teen (13-19 years)";
            case YOUNG_ADULT -> "Young Adult (20-35 years)";
            case ADULT -> "Adult (36-55 years)";
            case SENIOR -> "Senior (56+ years)";
            default -> throw new IllegalArgumentException();
        };
    }
}