package com.tov.controller;

import net.minidev.json.JSONObject;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tov.domain.CarEntity;
import com.tov.domain.OrderEntity;
import com.tov.dto.CarDto;
import com.tov.dto.OrderDto;
import com.tov.dto.assembler.CarDtoAssembler;
import com.tov.dto.assembler.OrderDtoAssembler;
import com.tov.service.CarService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "api/cars")
public class CarController {
    private final CarService carService;
    private final CarDtoAssembler carDtoAssembler;
    private final OrderDtoAssembler orderDtoAssembler;

    public CarController(CarService carService, CarDtoAssembler carDtoAssembler, OrderDtoAssembler orderDtoAssembler) {
        this.carService = carService;
        this.carDtoAssembler = carDtoAssembler;
        this.orderDtoAssembler = orderDtoAssembler;
    }

    @GetMapping(value = "/{carId}")
    public ResponseEntity<CarDto> getCar(@PathVariable Integer carId) {
        CarEntity carEntity = carService.findById(carId);
        CarDto carDto = carDtoAssembler.toModel(carEntity);
        return new ResponseEntity<>(carDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<CarDto>> getAllCars() {
        List<CarEntity> carEntityList = carService.findAll();
        CollectionModel<CarDto> carDtos = carDtoAssembler.toCollectionModel(carEntityList);
        return new ResponseEntity<>(carDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarDto> addCar(@RequestBody CarEntity carEntity) {
        CarEntity newCarEntity = carService.create(carEntity);
        CarDto carDto = carDtoAssembler.toModel(newCarEntity);
        return new ResponseEntity<>(carDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{carId}")
    public ResponseEntity<?> updateCar(@RequestBody CarEntity carEntity, @PathVariable Integer carId) {
        carService.update(carId, carEntity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{carId}")
    public ResponseEntity<?> deleteCar(@PathVariable Integer carId) {
        carService.delete(carId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/getAllOrdersForCarWithId/{carId}")
    public ResponseEntity<CollectionModel<OrderDto>> getAllOrdersForCarWithId(@PathVariable Integer carId) {
        CarEntity carEntity = carService.findById(carId);
        Set<OrderEntity> orderEntityList = carEntity.getOrders();
        CollectionModel<OrderDto> orderDtos = orderDtoAssembler.toCollectionModel(orderEntityList);
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value = "/relation")
    public ResponseEntity<?> addAnimatorAgencyRelationship(@RequestBody JSONObject jsonObject) {
        carService.addCarOrderRelationship(jsonObject.getAsString("car_name"), jsonObject.getAsString("address"));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}