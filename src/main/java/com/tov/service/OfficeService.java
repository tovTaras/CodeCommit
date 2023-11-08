package com.tov.service;

import com.tov.domain.OfficeEntity;

import java.util.List;

public interface OfficeService extends GeneralService<OfficeEntity, Integer>{
    List<OfficeEntity> getOfficeEntitiesByCityId(Integer cityId);
}