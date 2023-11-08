package com.tov.service;

import com.tov.domain.DeliveryPersonEntity;

public interface DeliveryPersonService extends GeneralService<DeliveryPersonEntity, Integer>{
    void createTablesWithCursor();
}