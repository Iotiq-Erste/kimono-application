package com.iotiq.application.entity.product.productFilters;

import com.iotiq.application.entity.product.productFilters.productEnums.DesignBodyPartEnum;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Embeddable
//DesignKörperstelle
public class DesignBodyPart {

    //DesignKörperstelle
    @ElementCollection(targetClass = DesignBodyPartEnum.class)
    @Enumerated(EnumType.STRING)
    private List<DesignBodyPartEnum> designBodyPartEnum;
}
