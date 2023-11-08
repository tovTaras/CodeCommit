package com.tov.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tov.domain.DeliveryEntity;
import com.tov.dto.DeliveryDto;
import com.tov.dto.assembler.DeliveryDtoAssembler;
import com.tov.service.DeliveryService;

import java.util.List;

@RestController
@RequestMapping(value = "api/deliveries")
public class DeliveryController {
    private final DeliveryService deliveryService;
    private final DeliveryDtoAssembler deliveryDtoAssembler;

    public DeliveryController(DeliveryService deliveryService, DeliveryDtoAssembler deliveryDtoAssembler) {
        this.deliveryService = deliveryService;
        this.deliveryDtoAssembler = deliveryDtoAssembler;
    }

    @GetMapping(value = "/{deliveryId}")
    public ResponseEntity<DeliveryDto> getDelivery(@PathVariable Integer deliveryId) {
        DeliveryEntity deliveryEntity = deliveryService.findById(deliveryId);
        DeliveryDto deliveryDto = deliveryDtoAssembler.toModel(deliveryEntity);
        return new ResponseEntity<>(deliveryDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<DeliveryDto>> getAllDeliveries() {
        List<DeliveryEntity> entityList = deliveryService.findAll();
        CollectionModel<DeliveryDto> dtos = deliveryDtoAssembler.toCollectionModel(entityList);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DeliveryDto> addDelivery(@RequestBody DeliveryEntity entity) {
        DeliveryEntity newEntity = deliveryService.create(entity);
        DeliveryDto dto = deliveryDtoAssembler.toModel(newEntity);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{deliveryId}")
    public ResponseEntity<?> updateDelivery(@RequestBody DeliveryEntity entity, @PathVariable Integer deliveryId) {
        deliveryService.update(deliveryId, entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{deliveryId}")
    public ResponseEntity<?> deleteDelivery(@PathVariable Integer deliveryId) {
        deliveryService.delete(deliveryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(value = "/withCityId/{cityId}")
    public ResponseEntity<CollectionModel<DeliveryDto>> getAllWithDeliveryPersonId(@PathVariable Integer deliveryPersonId) {
        List<DeliveryEntity> entityList = deliveryService.getDeliveryEntitiesByDeliveryPersonId(deliveryPersonId);
        CollectionModel<DeliveryDto> dtos = deliveryDtoAssembler.toCollectionModel(entityList);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}