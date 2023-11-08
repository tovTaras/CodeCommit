package com.tov.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tov.domain.OrderEntity;
import com.tov.domain.CarEntity;
import com.tov.dto.OrderDto;
import com.tov.dto.CarDto;
import com.tov.dto.assembler.OrderDtoAssembler;
import com.tov.dto.assembler.CarDtoAssembler;
import com.tov.service.OrderService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "api/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderDtoAssembler orderDtoAssembler;
    private final CarDtoAssembler carDtoAssembler;

    public OrderController(OrderService orderService, OrderDtoAssembler orderDtoAssembler, CarDtoAssembler carDtoAssembler) {
        this.orderService = orderService;
        this.orderDtoAssembler = orderDtoAssembler;
        this.carDtoAssembler = carDtoAssembler;
    }

    @GetMapping(value = "/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Integer orderId) {
        OrderEntity orderEntity = orderService.findById(orderId);
        OrderDto orderDto = orderDtoAssembler.toModel(orderEntity);
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<OrderDto>> getAllOrders() {
        List<OrderEntity> orderEntityList = orderService.findAll();
        CollectionModel<OrderDto> orderDtos = orderDtoAssembler.toCollectionModel(orderEntityList);
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDto> addOrder(@RequestBody OrderEntity orderEntity) {
        OrderEntity newOrderEntity = orderService.create(orderEntity);
        OrderDto orderDto = orderDtoAssembler.toModel(newOrderEntity);
        return new ResponseEntity<>(orderDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{orderId}")
    public ResponseEntity<?> updateOrder(@RequestBody OrderEntity orderEntity, @PathVariable Integer orderId) {
        orderService.update(orderId, orderEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Integer orderId) {
        orderService.delete(orderId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getAllCarsForOrderWithId/{orderId}")
    public ResponseEntity<CollectionModel<CarDto>> getAllCarsForOrderWithId(@PathVariable Integer orderId) {
        OrderEntity orderEntity = orderService.findById(orderId);
        Set<CarEntity> carEntityList = orderEntity.getCars();
        CollectionModel<CarDto> carDtos = carDtoAssembler.toCollectionModel(carEntityList);
        return new ResponseEntity<>(carDtos, HttpStatus.OK);
    }

}