package com.tov.service;

import com.tov.domain.DeliveryEntity;

import java.util.List;

public interface DeliveryService extends GeneralService<DeliveryEntity, Integer>{
    List<DeliveryEntity> getDeliveryEntitiesByDeliveryPersonId(Integer deliveryPersonId);
}