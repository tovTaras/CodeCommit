package com.tov.service;

import com.tov.domain.CarSizeEntity;

import java.math.BigDecimal;

public interface CarSizeService extends GeneralService<CarSizeEntity, Integer>{
    void insertTenCarSizes(String diameter, Float ingredientCostCoef,String name,String price);
    BigDecimal getAverageCarPrice();

}