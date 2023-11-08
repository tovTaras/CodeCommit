package com.tov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import com.tov.domain.CarSizeEntity;

import java.math.BigDecimal;

@Repository
public interface CarSizeRepository extends JpaRepository<CarSizeEntity, Integer> {

    @Procedure("insertTenCarSizes")
    void insertTenCarSizes(String diameter, Float ingredientCostCoef,String name,String price);
    @Query(value = "CALL CalcAverageCarPrice();", nativeQuery = true)
    BigDecimal getAverageSalary();
}