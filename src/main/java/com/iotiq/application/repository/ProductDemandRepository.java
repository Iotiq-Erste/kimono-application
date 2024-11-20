package com.iotiq.application.repository;

import com.iotiq.application.domain.Customer;
import com.iotiq.application.domain.ProductDemand;
import com.iotiq.application.domain.Seller;
import com.iotiq.application.domain.enums.Skill;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface ProductDemandRepository extends JpaRepository<ProductDemand, UUID> {

    @Query("select pd from ProductDemand pd left join pd.sustainability.skills skills where pd.id=:id and pd.isActive = true and (pd.sustainability.skills is empty or skills in :skills)")
    Optional<ProductDemand> findByIdAndIsActiveTrueSustainabilitySkillsEmptyOrSustainabilitySkillsIn(UUID id, Set<Skill> skills);

    Page<ProductDemand> findAllByCustomerAndIsActiveTrue(Customer customer, Pageable pageable);
    Optional<ProductDemand> findByIdAndCustomerAndIsActiveTrue(UUID id, Customer customer);

    @Query("select pd from ProductDemand pd left join pd.sustainability.skills skills where pd.isActive = true and pd.seller is null and (pd.sustainability.skills is empty or skills in :skills)")
    Page<ProductDemand> findByIsActiveTrueAndSellerIsNullAndSustainabilitySkillsEmptyOrSustainabilitySkillsIn(Pageable pageable, Set<Skill> skills);

    Page<ProductDemand> findAllBySeller(Pageable pageable, Seller seller);

    @Query("SELECT pd FROM ProductDemand pd left join pd.sustainability.skills skills  WHERE pd.id = :id AND pd.isActive = true AND (pd.seller IS NULL OR pd.seller = :seller) AND (pd.sustainability.skills is empty or skills in :skills)")
    Optional<ProductDemand> findByIdAndIsActiveAndSellerIsNullOrSellerAndSustainabilitySkillsEmptyOrSustainabilitySkillsIn(UUID id, Seller seller, Set<Skill> skills);
}
