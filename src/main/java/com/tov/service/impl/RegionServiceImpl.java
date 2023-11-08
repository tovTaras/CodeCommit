package com.tov.service.impl;

import org.springframework.stereotype.Service;
import com.tov.domain.RegionEntity;
import com.tov.exception.EntityNotFoundException;
import com.tov.repository.RegionRepository;
import com.tov.service.RegionService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    public RegionServiceImpl(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @Override
    public List<RegionEntity> findAll() {
        return regionRepository.findAll();
    }

    @Override
    public RegionEntity findById(Integer id) throws EntityNotFoundException{
        return regionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Region", String.valueOf(id)));
    }

    @Override
    @Transactional
    public RegionEntity create(RegionEntity entity) {
        regionRepository.save(entity);
        return entity;
    }

    @Override
    public void update(Integer id, RegionEntity entity) {
        RegionEntity regionEntity = findById(id);
        regionEntity.setName(entity.getName());
        regionRepository.save(regionEntity);
    }

    @Override
    public void delete(Integer id) {
        RegionEntity regionEntity = findById(id);
        regionRepository.delete(regionEntity);
    }
    
}