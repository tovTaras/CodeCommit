package com.tov.service.impl;

import org.springframework.stereotype.Service;
import com.tov.domain.CarSizeEntity;
import com.tov.exception.EntityNotFoundException;
import com.tov.repository.CarSizeRepository;
import com.tov.service.CarSizeService;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
public class CarSizeServiceImpl implements CarSizeService {

    private final CarSizeRepository carSizeRepository;

    public CarSizeServiceImpl(CarSizeRepository carSizeRepository) {
        this.carSizeRepository = carSizeRepository;
    }

    @Override
    public List<CarSizeEntity> findAll() {
        return carSizeRepository.findAll();
    }

    @Override
    public CarSizeEntity findById(Integer id) {
        return carSizeRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("CarSize", String.valueOf(id)));
    }

    @Override
    @Transactional
    public CarSizeEntity create(CarSizeEntity entity) {
        carSizeRepository.save(entity);
        return entity;
    }

    @Override
    public void update(Integer id, CarSizeEntity entity) {
        CarSizeEntity carSizeEntity = findById(id);
        carSizeEntity.setName(entity.getName());
        carSizeEntity.setDiameter(entity.getDiameter());
        carSizeEntity.setIngredientCostCoef(entity.getIngredientCostCoef());
        carSizeEntity.setPrice(entity.getPrice());
        carSizeRepository.save(carSizeEntity);
    }

    @Override
    public void delete(Integer id) {
        CarSizeEntity carSizeEntity = findById(id);
        carSizeRepository.delete(carSizeEntity);
    }
    @Override
    public void insertTenCarSizes(String diameter, Float ingredientCostCoef,String name,String price){
         carSizeRepository.insertTenCarSizes(diameter, ingredientCostCoef, name, price);
    };
    @Override
    public BigDecimal getAverageCarPrice() {
        return carSizeRepository.getAverageSalary();
    }

}