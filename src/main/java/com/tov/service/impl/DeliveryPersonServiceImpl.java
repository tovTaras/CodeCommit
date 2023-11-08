package com.tov.service.impl;

import org.springframework.stereotype.Service;
import com.tov.domain.DeliveryPersonEntity;
import com.tov.exception.EntityNotFoundException;
import com.tov.repository.DeliveryPersonRepository;
import com.tov.service.DeliveryPersonService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DeliveryPersonServiceImpl implements DeliveryPersonService {

    private final DeliveryPersonRepository deliveryPersonRepository;

    public DeliveryPersonServiceImpl(DeliveryPersonRepository deliveryPersonRepository) {
        this.deliveryPersonRepository = deliveryPersonRepository;
    }

    @Override
    public List<DeliveryPersonEntity> findAll() {
        return deliveryPersonRepository.findAll();
    }

    @Override
    public DeliveryPersonEntity findById(Integer id) {
        return deliveryPersonRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("DeliveryPerson", String.valueOf(id)));
    }

    @Override
    @Transactional
    public DeliveryPersonEntity create(DeliveryPersonEntity entity) {
        try {
            deliveryPersonRepository.save(entity);
            return entity;
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public void update(Integer id, DeliveryPersonEntity entity) {
        try{
            DeliveryPersonEntity deliveryPersonEntity = findById(id);
            deliveryPersonEntity.setLatitude(entity.getLatitude());
            deliveryPersonEntity.setLongitude(entity.getLongitude());
            deliveryPersonEntity.setSurname(entity.getSurname());
            deliveryPersonEntity.setName(entity.getName());
            deliveryPersonEntity.setPhone(entity.getPhone());
            deliveryPersonEntity.setAdress(entity.getAdress());
            deliveryPersonRepository.save(deliveryPersonEntity);
        }catch (Exception e){
            return;
        }

    }

    @Override
    public void delete(Integer id) {
        DeliveryPersonEntity deliveryPersonEntity = findById(id);
        deliveryPersonRepository.delete(deliveryPersonEntity);
    }

    @Override
    public void createTablesWithCursor() {
        deliveryPersonRepository.createTablesWithCursor();
    }
}