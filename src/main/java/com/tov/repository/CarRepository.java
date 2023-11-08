package com.tov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import com.tov.domain.CarEntity;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Integer> {
    @Procedure("AddCarOrderRelationship")
    void addCarOrderRelationship(String car_name, String address);
}