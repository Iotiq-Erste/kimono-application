package com.iotiq.application.domain;

import com.iotiq.application.domain.enums.SpecificBodyPart;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MedicalData {
    private String ageGroup;
    private String clothingSizeDetermination;
    private String brandPreferences;
    private List<String> clothingSelection;
    @ElementCollection(targetClass = SpecificBodyPart.class)
    @Enumerated(EnumType.STRING)
    private List<SpecificBodyPart> bodyRegions;
    private String medicalExamination;
    private List<String> allergiesSensitivities;
    private List<String> pastHealthIssues;
    private List<String> treatmentWithMedications;
    private List<String> medicalHistory;
    private String skinDisease;
}
