package com.tov.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tov.domain.RegionEntity;
import com.tov.dto.RegionDto;
import com.tov.dto.assembler.RegionDtoAssembler;
import com.tov.service.RegionService;

import java.util.List;

@RestController
@RequestMapping(value = "api/regions")
public class RegionController {
    private final RegionService regionService;
    private final RegionDtoAssembler regionDtoAssembler;

    public RegionController(RegionService regionService, RegionDtoAssembler regionDtoAssembler) {
        this.regionService = regionService;
        this.regionDtoAssembler = regionDtoAssembler;
    }

    @GetMapping(value = "/{regionId}")
    public ResponseEntity<RegionDto> getRegion(@PathVariable Integer regionId) {
        RegionEntity regionEntity = regionService.findById(regionId);
        RegionDto regionDto = regionDtoAssembler.toModel(regionEntity);
        return new ResponseEntity<>(regionDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<RegionDto>> getAllRegions() {
        List<RegionEntity> entityList = regionService.findAll();
        CollectionModel<RegionDto> dtos = regionDtoAssembler.toCollectionModel(entityList);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RegionDto> addRegion(@RequestBody RegionEntity entity) {
        try {
            RegionEntity newEntity = regionService.create(entity);
            RegionDto dto = regionDtoAssembler.toModel(newEntity);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        }catch (Exception e){
            return null;
        }

    }

    @PutMapping(value = "/{regionId}")
    public ResponseEntity<?> updateRegion(@RequestBody RegionEntity entity, @PathVariable Integer regionId) {
        try {
            regionService.update(regionId, entity);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return null;
        }

    }

    @DeleteMapping(value = "/{regionId}")
    public ResponseEntity<?> deleteRegion(@PathVariable Integer regionId) {
        try {
            regionService.delete(regionId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return null;
        }

    }
}