package com.tov.service.impl;

import org.springframework.stereotype.Service;
import com.tov.domain.CityEntity;
import com.tov.exception.EntityNotFoundException;
import com.tov.repository.CityRepository;
import com.tov.service.CityService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public List<CityEntity> findAll() {
        return cityRepository.findAll();
    }

    @Override
    public CityEntity findById(Integer id) throws EntityNotFoundException{
        return cityRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("City", String.valueOf(id)));
    }

    @Override
    @Transactional
    public CityEntity create(CityEntity entity) {
        cityRepository.save(entity);
        return entity;
    }

    @Override
    public void update(Integer id, CityEntity entity) {
        CityEntity cityEntity = findById(id);
        cityEntity.setName(entity.getName());
        cityEntity.setRegion(entity.getRegion());
        cityRepository.save(cityEntity);
    }

    @Override
    public void delete(Integer id) {
        CityEntity cityEntity = findById(id);
        cityRepository.delete(cityEntity);
    }

    @Override
    public CityEntity addCityWithProcedure(String name, Integer region_id) {
        return cityRepository.addCityWithProcedure(name, region_id);
    }

    @Override
    public List<CityEntity> getCityEntitiesByRegionId(Integer regionId) {
        return cityRepository.getCityEntitiesByRegionId(regionId);
    }
}