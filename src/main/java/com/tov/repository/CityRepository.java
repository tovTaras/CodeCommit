package com.tov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import com.tov.domain.CityEntity;

import java.util.List;

@Repository
public interface CityRepository extends  JpaRepository<CityEntity, Integer>{
    @Procedure("CityParamInsert")
    CityEntity addCityWithProcedure(String name, Integer region_id);

    List<CityEntity> getCityEntitiesByRegionId(Integer regionId);
}
