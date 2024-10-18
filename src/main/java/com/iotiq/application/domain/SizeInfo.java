package com.iotiq.application.domain;

import com.iotiq.application.domain.enums.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SizeInfo {

    private Size overallSize;
    private Integer neckCircumference;
    private Integer chestCircumference;
    private Integer chestCircumferenceHorizontal;
    private Integer leftArmLength;
    private Integer rightArmLength;
    private Integer height;
    private Integer weight;
    private Integer shoeSize;
    private String otherNotes;

}
