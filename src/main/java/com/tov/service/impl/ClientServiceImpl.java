package com.tov.service.impl;

import org.springframework.stereotype.Service;
import com.tov.domain.ClientEntity;
import com.tov.exception.EntityNotFoundException;
import com.tov.repository.ClientRepository;
import com.tov.service.ClientService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientEntity> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public ClientEntity findById(Integer id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Client", String.valueOf(id)));
    }

    @Override
    @Transactional
    public ClientEntity create(ClientEntity entity) {
        clientRepository.save(entity);
        return entity;
    }

    @Override
    public void update(Integer id, ClientEntity entity) {
        ClientEntity clientEntity = findById(id);
        clientEntity.setName(entity.getName());
        clientEntity.setSurname(entity.getSurname());
        clientEntity.setBirthday(entity.getBirthday());
        clientEntity.setGender(entity.getGender());
        clientEntity.setEmail(entity.getEmail());
        clientEntity.setPhone(entity.getPhone());
        clientEntity.setCity(entity.getCity());
        clientRepository.save(clientEntity);
    }

    @Override
    public void delete(Integer id) {
        ClientEntity clientEntity = findById(id);
        clientRepository.delete(clientEntity);
    }

    @Override
    public List<ClientEntity> getClientEntitiesByCityId(Integer cityId) {
        return clientRepository.getClientEntitiesByCityId(cityId);
    }
}