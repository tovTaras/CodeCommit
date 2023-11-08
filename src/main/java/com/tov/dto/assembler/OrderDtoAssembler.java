package com.tov.dto.assembler;

import com.tov.controller.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.tov.domain.OrderEntity;
import com.tov.dto.OrderDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderDtoAssembler implements RepresentationModelAssembler<OrderEntity, OrderDto> {
    @Override
    public OrderDto toModel(OrderEntity entity) {
        OrderDto orderDto = OrderDto.builder()
                .id(entity.getId())
                .office(entity.getOffice().getStreetAddress())
                .client(entity.getClient().getSurname())
                .city(entity.getCity().getName())
                .delivery(entity.getDelivery().getStatus())
                .time(entity.getTime().toString())
                .totalPrice(entity.getTotalPrice())
                .adressee(entity.getAdressee())
                .build();
        orderDto.add(linkTo(methodOn(OrderController.class).getOrder(orderDto.getId())).withSelfRel(),
                linkTo(methodOn(CityController.class).getCity(entity.getCity().getId())).withRel("city"),
                linkTo(methodOn(ClientController.class).getClient(entity.getClient().getId())).withRel("client"),
                linkTo(methodOn(DeliveryController.class).getDelivery(entity.getDelivery().getId())).withRel("delivery"),
                linkTo(methodOn(OfficeController.class).getOffice(entity.getOffice().getId())).withRel("office")
        );
        return orderDto;
    }

    @Override
    public CollectionModel<OrderDto> toCollectionModel(Iterable<? extends OrderEntity> entities) {
        CollectionModel<OrderDto> orderDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(OrderController.class).getAllOrders()).withSelfRel();
        orderDtos.add(selfLink);
        return orderDtos;
    }
}