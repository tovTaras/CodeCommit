package com.tov.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.tov.controller.CarSizeController;
import com.tov.domain.CarSizeEntity;
import com.tov.dto.CarSizeDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CarSizeDtoAssembler implements RepresentationModelAssembler<CarSizeEntity, CarSizeDto> {
    @Override
    public CarSizeDto toModel(CarSizeEntity entity) {
        CarSizeDto carSizeDto = CarSizeDto.builder()
                .id(entity.getId())
                .diameter(entity.getDiameter())
                .ingredientCostCoef((float) entity.getIngredientCostCoef())
                .name(entity.getName())
                .price(entity.getPrice())
                .build();
        Link selfLink = linkTo(methodOn(CarSizeController.class).getCarSize(carSizeDto.getId())).withSelfRel();
        carSizeDto.add(selfLink);
        return carSizeDto;
    }

    @Override
    public CollectionModel<CarSizeDto> toCollectionModel(Iterable<? extends CarSizeEntity> entities) {
        CollectionModel<CarSizeDto> carSizeDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CarSizeController.class).getAllCarSizes()).withSelfRel();
        carSizeDtos.add(selfLink);
        return carSizeDtos;
    }
}