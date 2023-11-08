package com.tov.service;

import com.tov.domain.CityEntity;

import java.util.List;

public interface CityService extends GeneralService<CityEntity, Integer>{
    CityEntity addCityWithProcedure(String name, Integer region_id);
    List<CityEntity> getCityEntitiesByRegionId(Integer regionId);

}