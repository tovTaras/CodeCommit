package com.tov.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tov.domain.CarSizeEntity;
import com.tov.dto.CarSizeDto;
import com.tov.dto.assembler.CarSizeDtoAssembler;
import com.tov.service.CarSizeService;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(value = "api/carSizes")
public class CarSizeController {
    private final CarSizeService carSizeService;
    private final CarSizeDtoAssembler carSizeDtoAssembler;

    public CarSizeController(CarSizeService carSizeService, CarSizeDtoAssembler carSizeDtoAssembler) {
        this.carSizeService = carSizeService;
        this.carSizeDtoAssembler = carSizeDtoAssembler;
    }

    @GetMapping(value = "/{carSizeId}")
    public ResponseEntity<CarSizeDto> getCarSize(@PathVariable Integer carSizeId) {
        CarSizeEntity carSizeEntity = carSizeService.findById(carSizeId);
        CarSizeDto carSizeDto = carSizeDtoAssembler.toModel(carSizeEntity);
        return new ResponseEntity<>(carSizeDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<CarSizeDto>> getAllCarSizes() {
        List<CarSizeEntity> entityList = carSizeService.findAll();
        CollectionModel<CarSizeDto> dtos = carSizeDtoAssembler.toCollectionModel(entityList);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarSizeDto> addCarSize(@RequestBody CarSizeEntity entity) {
        CarSizeEntity newEntity = carSizeService.create(entity);
        CarSizeDto dto = carSizeDtoAssembler.toModel(newEntity);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{carSizeId}")
    public ResponseEntity<?> updateCarSize(@RequestBody CarSizeEntity entity, @PathVariable Integer carSizeId) {
        carSizeService.update(carSizeId, entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{carSizeId}")
    public ResponseEntity<?> deleteCarSize(@PathVariable Integer carSizeId) {
        carSizeService.delete(carSizeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/procedure_inserts")
    public ResponseEntity<?> insertTenCarSizes() {
        carSizeService.insertTenCarSizes("NewCarDiameter", 1f, "NewName", "NewPrice");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/avg_price_procedure")
    public ResponseEntity<BigDecimal> getAverageSalary() {
        BigDecimal avgSalary = carSizeService.getAverageCarPrice();
        return new ResponseEntity<>(avgSalary, HttpStatus.OK);
    }
}