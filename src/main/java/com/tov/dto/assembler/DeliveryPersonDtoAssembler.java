package com.tov.dto.assembler;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.tov.controller.DeliveryPersonController;
import com.tov.domain.DeliveryPersonEntity;
import com.tov.dto.DeliveryPersonDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DeliveryPersonDtoAssembler implements RepresentationModelAssembler<DeliveryPersonEntity, DeliveryPersonDto> {
    @Override
    public DeliveryPersonDto toModel(DeliveryPersonEntity entity) {
        DeliveryPersonDto deliveryPersonDto = DeliveryPersonDto.builder()
                .id(entity.getId())
                .latitude((float) entity.getLatitude())
                .longitude((float) entity.getLongitude())
                .surname(entity.getSurname())
                .name(entity.getName())
                .phone(entity.getPhone())
                .adress(entity.getAdress())
                .build();
        Link selfLink = linkTo(methodOn(DeliveryPersonController.class).getDeliveryPerson(deliveryPersonDto.getId())).withSelfRel();
        deliveryPersonDto.add(selfLink);
        return deliveryPersonDto;
    }

    @Override
    public CollectionModel<DeliveryPersonDto> toCollectionModel(Iterable<? extends DeliveryPersonEntity> entities) {
        CollectionModel<DeliveryPersonDto> deliveryPersonDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(DeliveryPersonController.class).getAllDeliveryPersons()).withSelfRel();
        deliveryPersonDtos.add(selfLink);
        return deliveryPersonDtos;
    }
}