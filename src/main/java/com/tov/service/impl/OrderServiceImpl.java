package com.tov.service.impl;

import org.springframework.stereotype.Service;
import com.tov.domain.OrderEntity;
import com.tov.exception.EntityNotFoundException;
import com.tov.repository.OrderRepository;
import com.tov.service.OrderService;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<OrderEntity> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public OrderEntity findById(Integer id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order", String.valueOf(id)));
    }

    @Override
    @Transactional
    public OrderEntity create(OrderEntity entity) {
        orderRepository.save(entity);
        return entity;
    }

    @Override
    public void update(Integer id, OrderEntity entity) {
        OrderEntity orderEntity = findById(id);
        orderEntity.setOffice(entity.getOffice());
        orderEntity.setCity(entity.getCity());
        orderEntity.setClient(entity.getClient());
        orderEntity.setDelivery(entity.getDelivery());
        orderEntity.setTime(entity.getTime());
        orderEntity.setTotalPrice(entity.getTotalPrice());
        orderEntity.setAdressee(entity.getAdressee());
        orderRepository.save(orderEntity);
    }

    @Override
    public void delete(Integer id) {
        OrderEntity orderEntity = findById(id);
        orderRepository.delete(orderEntity);
    }
}