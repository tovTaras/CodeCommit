package com.tov.service.impl;

import org.springframework.stereotype.Service;
import com.tov.domain.CarEntity;
import com.tov.exception.EntityNotFoundException;
import com.tov.repository.CarRepository;
import com.tov.service.CarService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public List<CarEntity> findAll() {
        return carRepository.findAll();
    }

    @Override
    public CarEntity findById(Integer id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car", String.valueOf(id)));
    }

    @Override
    @Transactional
    public CarEntity create(CarEntity entity) {
        carRepository.save(entity);
        return entity;
    }

    @Override
    public void update(Integer id, CarEntity entity) {
        CarEntity carEntity = findById(id);
        carEntity.setName(entity.getName());
        carEntity.setCarSizeByCarSizeId(entity.getCarSizeByCarSizeId());
        carRepository.save(carEntity);
    }

    @Override
    public void delete(Integer id) {
        CarEntity carEntity = findById(id);
        carRepository.delete(carEntity);
    }

    @Override
    public void addCarOrderRelationship(String car_name, String address) {
        carRepository.addCarOrderRelationship(car_name, address);
    }

}