package com.iotiq.application.domain;

import com.iotiq.application.domain.enums.ApplicationArea;
import com.iotiq.application.domain.enums.Capacity;
import com.iotiq.application.domain.enums.Skill;
import com.iotiq.user.domain.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Seller extends AbstractPersistable<UUID> {

    public static final String ENTITY_NAME = "seller";

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    private String shopName;

    private String taxNumber;

    private boolean isActive;

    @ElementCollection(targetClass = Skill.class)
    @Enumerated(EnumType.STRING)
    private List<Skill> skills;

    private Capacity capacity;

    @ElementCollection(targetClass = ApplicationArea.class)
    @Enumerated(EnumType.STRING)
    private List<ApplicationArea> applicationAreas;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products;
}
