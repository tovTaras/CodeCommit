package com.tov.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tov.domain.OfficeEntity;
import com.tov.dto.OfficeDto;
import com.tov.dto.assembler.OfficeDtoAssembler;
import com.tov.service.OfficeService;

import java.util.List;

@RestController
@RequestMapping(value = "api/offices")
public class OfficeController {
    private final OfficeService officeService;
    private final OfficeDtoAssembler officeDtoAssembler;

    public OfficeController(OfficeService officeService, OfficeDtoAssembler officeDtoAssembler) {
        this.officeService = officeService;
        this.officeDtoAssembler = officeDtoAssembler;
    }

    @GetMapping(value = "/{officeId}")
    public ResponseEntity<OfficeDto> getOffice(@PathVariable Integer officeId) {
        OfficeEntity officeEntity = officeService.findById(officeId);
        OfficeDto officeDto = officeDtoAssembler.toModel(officeEntity);
        return new ResponseEntity<>(officeDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<OfficeDto>> getAllOffices() {
        List<OfficeEntity> entityList = officeService.findAll();
        CollectionModel<OfficeDto> dtos = officeDtoAssembler.toCollectionModel(entityList);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OfficeDto> addOffice(@RequestBody OfficeEntity entity) {
        OfficeEntity newEntity = officeService.create(entity);
        OfficeDto dto = officeDtoAssembler.toModel(newEntity);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{officeId}")
    public ResponseEntity<?> updateOffice(@RequestBody OfficeEntity entity, @PathVariable Integer officeId) {
        officeService.update(officeId, entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{officeId}")
    public ResponseEntity<?> deleteOffice(@PathVariable Integer officeId) {
        officeService.delete(officeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(value = "/withCityId/{cityId}")
    public ResponseEntity<CollectionModel<OfficeDto>> getAllWithCityId(@PathVariable Integer cityId) {
        List<OfficeEntity> entityList = officeService.getOfficeEntitiesByCityId(cityId);
        CollectionModel<OfficeDto> dtos = officeDtoAssembler.toCollectionModel(entityList);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}