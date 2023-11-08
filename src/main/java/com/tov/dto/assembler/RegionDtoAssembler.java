package com.tov.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.tov.controller.RegionController;
import com.tov.domain.RegionEntity;
import com.tov.dto.RegionDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RegionDtoAssembler implements RepresentationModelAssembler<RegionEntity, RegionDto> {
    @Override
    public RegionDto toModel(RegionEntity entity) {
        RegionDto regionDto = RegionDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .build();
        Link selfLink = linkTo(methodOn(RegionController.class).getRegion(regionDto.getId())).withSelfRel();
        regionDto.add(selfLink);
        return regionDto;
    }

    @Override
    public CollectionModel<RegionDto> toCollectionModel(Iterable<? extends RegionEntity> entities) {
        CollectionModel<RegionDto> regionDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(RegionController.class).getAllRegions()).withSelfRel();
        regionDtos.add(selfLink);
        return regionDtos;
    }
}