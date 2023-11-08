package com.tov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tov.domain.DeliveryEntity;

import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryEntity, Integer> {
    List<DeliveryEntity> getDeliveryEntitiesByDeliveryPersonId(Integer deliveryPersonId);
}