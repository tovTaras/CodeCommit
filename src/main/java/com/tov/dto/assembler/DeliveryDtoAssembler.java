package com.tov.dto.assembler;

import com.tov.controller.DeliveryPersonController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.tov.controller.DeliveryController;
import com.tov.domain.DeliveryEntity;
import com.tov.dto.DeliveryDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DeliveryDtoAssembler implements RepresentationModelAssembler<DeliveryEntity, DeliveryDto> {
    @Override
    public DeliveryDto toModel(DeliveryEntity entity) {
        DeliveryDto deliveryDto = DeliveryDto.builder()
                .id(entity.getId())
                .deliveryPerson(entity.getDeliveryPerson().getSurname())
                .apartmentNumber(entity.getApartmentnumber())
                .status(entity.getStatus())
                .deliveryTimeAprox(entity.getDeliveryTimeAprox())
                .deliveryTimeReal(entity.getDeliveryTimeReal())
                .deliveryPayment(entity.getDeliveryPayment())
                .build();
        deliveryDto.add(linkTo(methodOn(DeliveryController.class).getDelivery(deliveryDto.getId())).withSelfRel(),
                linkTo(methodOn(DeliveryPersonController.class).getDeliveryPerson(entity.getDeliveryPerson().getId())).withRel("delivery_person")
        );
        return deliveryDto;
    }

    @Override
    public CollectionModel<DeliveryDto> toCollectionModel(Iterable<? extends DeliveryEntity> entities) {
        CollectionModel<DeliveryDto> deliveryDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(DeliveryController.class).getAllDeliveries()).withSelfRel();
        deliveryDtos.add(selfLink);
        return deliveryDtos;
    }
}