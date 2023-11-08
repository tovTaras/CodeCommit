package com.tov.service;

import com.tov.domain.ClientEntity;

import java.util.List;

public interface ClientService extends GeneralService<ClientEntity, Integer>{
    List<ClientEntity> getClientEntitiesByCityId(Integer cityId);
}