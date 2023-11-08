package com.tov.service.impl;

import org.springframework.stereotype.Service;
import com.tov.domain.DeliveryEntity;
import com.tov.exception.EntityNotFoundException;
import com.tov.repository.DeliveryRepository;
import com.tov.service.DeliveryService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryRepository deliveryRepository;

    public DeliveryServiceImpl(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public List<DeliveryEntity> findAll() {
        return deliveryRepository.findAll();
    }

    @Override
    public DeliveryEntity findById(Integer id) {
        return deliveryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Delivery", String.valueOf(id)));
    }

    @Override
    @Transactional
    public DeliveryEntity create(DeliveryEntity entity) {
        deliveryRepository.save(entity);
        return entity;
    }

    @Override
    public void update(Integer id, DeliveryEntity entity) {
        DeliveryEntity deliveryEntity = findById(id);
        deliveryEntity.setDeliveryPayment(Float.valueOf(entity.getDeliveryPayment()));
        deliveryEntity.setApartmentnumber(Integer.valueOf(entity.getApartmentnumber()));
        deliveryEntity.setStatus(entity.getStatus());
        deliveryEntity.setDeliveryTimeAprox(entity.getDeliveryTimeAprox());
        deliveryEntity.setDeliveryTimeReal(entity.getDeliveryTimeReal());
        deliveryEntity.setDeliveryPerson(entity.getDeliveryPerson());
        deliveryRepository.save(deliveryEntity);
    }

    @Override
    public void delete(Integer id) {
        DeliveryEntity deliveryEntity = findById(id);
        deliveryRepository.delete(deliveryEntity);
    }

    @Override
    public List<DeliveryEntity> getDeliveryEntitiesByDeliveryPersonId(Integer deliveryPersonId) {
        return deliveryRepository.getDeliveryEntitiesByDeliveryPersonId(deliveryPersonId);
    }
}