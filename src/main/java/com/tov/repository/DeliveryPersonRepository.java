package com.tov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import com.tov.domain.DeliveryPersonEntity;

@Repository
public interface DeliveryPersonRepository extends JpaRepository<DeliveryPersonEntity, Integer> {
    @Procedure("CreateTablesWithCursor")
    void createTablesWithCursor();
}