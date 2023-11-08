package com.tov.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.tov.controller.CarController;
import com.tov.domain.CarEntity;
import com.tov.dto.CarDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CarDtoAssembler implements RepresentationModelAssembler<CarEntity, CarDto> {
    @Override
    public CarDto toModel(CarEntity entity) {
        CarDto carDto = CarDto.builder()
                .id(entity.getId())
                .carSizeId(entity.getCarSizeByCarSizeId().getId())
                .name(entity.getName())
                .build();
        Link selfLink = linkTo(methodOn(CarController.class).getCar(carDto.getId())).withSelfRel();
        carDto.add(selfLink);
        return carDto;
    }

    @Override
    public CollectionModel<CarDto> toCollectionModel(Iterable<? extends CarEntity> entities) {
        CollectionModel<CarDto> carDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CarController.class).getAllCars()).withSelfRel();
        carDtos.add(selfLink);
        return carDtos;
    }
}