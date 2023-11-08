package com.tov.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tov.domain.CityEntity;
import com.tov.dto.CityDto;
import com.tov.dto.assembler.CityDtoAssembler;
import com.tov.service.CityService;
import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping(value = "api/cities")
public class CityController {
    @Autowired
    private final CityService cityService;
    @Autowired
    private final CityDtoAssembler cityDtoAssembler;

    public CityController(CityService cityService, CityDtoAssembler cityDtoAssembler) {
        this.cityService = cityService;
        this.cityDtoAssembler = cityDtoAssembler;
    }

    @GetMapping(value = "/{cityId}")
    public ResponseEntity<CityDto> getCity(@PathVariable Integer cityId) {
        CityEntity cityEntity = cityService.findById(cityId);
        CityDto cityDto = cityDtoAssembler.toModel(cityEntity);
        return new ResponseEntity<>(cityDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<CityDto>> getAllCities() {
        List<CityEntity> entityList = cityService.findAll();
        CollectionModel<CityDto> dtos = cityDtoAssembler.toCollectionModel(entityList);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CityDto> addCity(@RequestBody CityEntity entity) {
        try{
            CityEntity newEntity = cityService.create(entity);
            CityDto dto = cityDtoAssembler.toModel(newEntity);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        }
        catch(Exception e){
            return null;
        }

    }

    @PutMapping(value = "/{cityId}")
    public ResponseEntity<?> updateCity(@RequestBody CityEntity entity, @PathVariable Integer cityId) {
        try {
            cityService.update(cityId, entity);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return null;
        }
    }

    @DeleteMapping(value = "/{cityId}")
    public ResponseEntity<?> deleteCity(@PathVariable Integer cityId) {
        try {
            cityService.delete(cityId);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return null;
        }
    }

    @GetMapping(value = "/withRegionId/{regionId}")
    public ResponseEntity<CollectionModel<CityDto>> getAllWithCityId(@PathVariable Integer regionId) {
        List<CityEntity> entityList = cityService.getCityEntitiesByRegionId(regionId);
        CollectionModel<CityDto> dtos = cityDtoAssembler.toCollectionModel(entityList);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value = "/insert_procedure")
    public ResponseEntity<CityDto> addCityWithProcedure() {
        CityEntity newCity = cityService.addCityWithProcedure("Warsaw", 2);
        CityDto cityDto = cityDtoAssembler.toModel(newCity);
        return new ResponseEntity<>(cityDto, HttpStatus.CREATED);
    }
}