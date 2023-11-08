package com.tov.dto.assembler;

import com.tov.controller.CityController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.tov.controller.OfficeController;
import com.tov.domain.OfficeEntity;
import com.tov.dto.OfficeDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OfficeDtoAssembler implements RepresentationModelAssembler<OfficeEntity, OfficeDto> {
    @Override
    public OfficeDto toModel(OfficeEntity entity) {
        OfficeDto officeDto = OfficeDto.builder()
                .id(entity.getId())
                .streetAddress(entity.getStreetAddress())
                .phone(entity.getPhone())
                .officeHead(entity.getOfficeHead())
                .city(entity.getCity().getName())
                .build();
        officeDto.add(linkTo(methodOn(OfficeController.class).getOffice(officeDto.getId())).withSelfRel(),
                linkTo(methodOn(CityController.class).getCity(entity.getCity().getId())).withRel("city")
        );
        return officeDto;
    }

    @Override
    public CollectionModel<OfficeDto> toCollectionModel(Iterable<? extends OfficeEntity> entities) {
        CollectionModel<OfficeDto> officeDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(OfficeController.class).getAllOffices()).withSelfRel();
        officeDtos.add(selfLink);
        return officeDtos;
    }
}