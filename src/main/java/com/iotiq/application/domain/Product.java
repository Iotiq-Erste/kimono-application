package com.iotiq.application.domain;

import com.iotiq.application.domain.enums.Brand;
import com.iotiq.application.domain.enums.Category;
import com.iotiq.application.domain.enums.Certification;
import com.iotiq.application.domain.enums.Color;
import com.iotiq.application.domain.enums.DesignBodyPart;
import com.iotiq.application.domain.enums.FiberType;
import com.iotiq.application.domain.enums.Gender;
import com.iotiq.application.domain.enums.MaterialParameter;
import com.iotiq.application.domain.enums.Motif;
import com.iotiq.application.domain.enums.Neurodermatitis;
import com.iotiq.application.domain.enums.OekotexStandard;
import com.iotiq.application.domain.enums.PriceRange;
import com.iotiq.application.domain.enums.Rating;
import com.iotiq.application.domain.enums.Size;
import com.iotiq.application.domain.enums.SpecificBodyPart;
import com.iotiq.application.domain.enums.SpecificFunctionality;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Product extends AbstractPersistable<UUID> {

    private String title;

    private String description;

    @Embedded
    private Price price;

    private String imageUrl;

    @Embedded
    private AgeGroup ageGroup;

    @Embedded
    private ApplicationAreaGroup applicationAreaGroup;

    //Marken
    @Enumerated(EnumType.STRING)
    private Brand brand;

    //Kategorien
    @Enumerated(EnumType.STRING)
    private Category category;

    //Zertifizierung
    @ElementCollection(targetClass = Certification.class)
    @Enumerated(EnumType.STRING)
    private List<Certification> certifications;

    //Farben
    @Enumerated(EnumType.STRING)
    private Color color;

    @Embedded
    private Composition composition;

    @Embedded
    private Design design;

    //DesignKörperstelle
    @ElementCollection(targetClass = DesignBodyPart.class)
    @Enumerated(EnumType.STRING)
    private List<DesignBodyPart> designBodyParts;

    //Faser
    @ElementCollection(targetClass = FiberType.class)
    @Enumerated(EnumType.STRING)
    private List<FiberType> fiberTypes;

    // Geschlecht
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Embedded
    private Haptics haptics;

    @Embedded
    private MaterialBehavior materialBehavior;

    //Material Parameter Enum
    @Enumerated(EnumType.STRING)
    private MaterialParameter materialParameter;

    //Motiv
    @Enumerated(EnumType.STRING)
    private Motif motif;

    //Neurodermitisklasse
    @Enumerated(EnumType.STRING)
    private Neurodermatitis neurodermatitis;

    //Öko-Tex Standard
    @Enumerated(EnumType.STRING)
    private OekotexStandard oekotexStandard;

    //Preisklasse
    @Enumerated(EnumType.STRING)
    private PriceRange priceRange;

    //Bewertungen
    @Enumerated(EnumType.STRING)
    private Rating rating;

    //Basisgröße
    @Enumerated(EnumType.STRING)
    private List<Size> sizes;

    //Spezifische Körperstelle
    @ElementCollection(targetClass = SpecificBodyPart.class)
    @Enumerated(EnumType.STRING)
    private List<SpecificBodyPart> specificBodyParts;

    //Spezifische Funktionalität
    @ElementCollection(targetClass = SpecificFunctionality.class)
    @Enumerated(EnumType.STRING)
    private List<SpecificFunctionality> specificFunctionalities;

    @Embedded
    private Sustainability sustainability;

}
