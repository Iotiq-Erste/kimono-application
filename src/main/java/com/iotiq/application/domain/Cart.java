package com.iotiq.application.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Cart extends AbstractPersistable<UUID> {

    private UUID owner;

    @OneToMany
    private List<Product> products;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
