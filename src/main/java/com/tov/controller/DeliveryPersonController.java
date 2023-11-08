package com.tov.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tov.domain.DeliveryPersonEntity;
import com.tov.dto.DeliveryPersonDto;
import com.tov.dto.assembler.DeliveryPersonDtoAssembler;
import com.tov.service.DeliveryPersonService;

import java.util.List;

@RestController
@RequestMapping(value = "api/deliveryPersons")
public class DeliveryPersonController {
    private final DeliveryPersonService deliveryPersonService;
    private final DeliveryPersonDtoAssembler deliveryPersonDtoAssembler;

    public DeliveryPersonController(DeliveryPersonService deliveryPersonService, DeliveryPersonDtoAssembler deliveryPersonDtoAssembler) {
        this.deliveryPersonService = deliveryPersonService;
        this.deliveryPersonDtoAssembler = deliveryPersonDtoAssembler;
    }

    @GetMapping(value = "/{deliveryPersonId}")
    public ResponseEntity<DeliveryPersonDto> getDeliveryPerson(@PathVariable Integer deliveryPersonId) {
        DeliveryPersonEntity deliveryPersonEntity = deliveryPersonService.findById(deliveryPersonId);
        DeliveryPersonDto deliveryPersonDto = deliveryPersonDtoAssembler.toModel(deliveryPersonEntity);
        return new ResponseEntity<>(deliveryPersonDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<DeliveryPersonDto>> getAllDeliveryPersons() {
        List<DeliveryPersonEntity> entityList = deliveryPersonService.findAll();
        CollectionModel<DeliveryPersonDto> dtos = deliveryPersonDtoAssembler.toCollectionModel(entityList);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DeliveryPersonDto> addDeliveryPerson(@RequestBody DeliveryPersonEntity entity) {
        try{
            DeliveryPersonEntity newEntity = deliveryPersonService.create(entity);
            DeliveryPersonDto dto = deliveryPersonDtoAssembler.toModel(newEntity);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        }catch (Exception e){
            return null;
        }

    }

    @PutMapping(value = "/{deliveryPersonId}")
    public ResponseEntity<?> updateDeliveryPerson(@RequestBody DeliveryPersonEntity entity, @PathVariable Integer deliveryPersonId) {
        try{
            deliveryPersonService.update(deliveryPersonId, entity);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return null;
        }

    }

    @DeleteMapping(value = "/{deliveryPersonId}")
    public ResponseEntity<?> deleteDeliveryPerson(@PathVariable Integer deliveryPersonId) {
        deliveryPersonService.delete(deliveryPersonId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/cursor")
    public ResponseEntity<?> createTablesWithCursor() {
        deliveryPersonService.createTablesWithCursor();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}