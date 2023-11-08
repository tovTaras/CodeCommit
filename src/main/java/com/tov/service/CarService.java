package com.tov.service;

import com.tov.domain.CarEntity;

public interface CarService extends GeneralService<CarEntity, Integer>{
    void addCarOrderRelationship(String car_name, String address);
}