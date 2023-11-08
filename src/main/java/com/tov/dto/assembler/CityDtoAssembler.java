package com.tov.dto.assembler;

import com.tov.controller.RegionController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.tov.controller.CityController;
import com.tov.domain.CityEntity;
import com.tov.dto.CityDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class CityDtoAssembler implements RepresentationModelAssembler<CityEntity, CityDto> {
    @Override
    public CityDto toModel(CityEntity entity) {
        CityDto cityDto = CityDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .region(entity.getRegion().getName())
                .build();
        Link selfLink = linkTo(methodOn(CityController.class).getCity(cityDto.getId())).withSelfRel();
        cityDto.add(selfLink);
        cityDto.add(linkTo(methodOn(CityController.class).getCity(cityDto.getId())).withSelfRel(),
                linkTo(methodOn(RegionController.class).getRegion(entity.getRegion().getId())).withRel("region")
        );
        return cityDto;
    }

    @Override
    public CollectionModel<CityDto> toCollectionModel(Iterable<? extends CityEntity> entities) {
        CollectionModel<CityDto> cityDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CityController.class).getAllCities()).withSelfRel();
        cityDtos.add(selfLink);
        return cityDtos;
    }
}