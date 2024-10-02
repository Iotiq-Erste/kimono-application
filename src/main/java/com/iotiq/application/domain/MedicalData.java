package com.iotiq.application.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MedicalData {
    private List<String> bodyRegions;
    private List<String> textileMaterials;
    private List<String> neurodermatitisConditions;
    private List<String> symptoms;
}
