package com.tov.dto.assembler;

import com.tov.controller.CityController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;
import com.tov.controller.ClientController;
import com.tov.domain.ClientEntity;
import com.tov.dto.ClientDto;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ClientDtoAssembler implements RepresentationModelAssembler<ClientEntity, ClientDto> {
    @Override
    public ClientDto toModel(ClientEntity entity) {
        ClientDto clientDto = ClientDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .surname(entity.getSurname())
                .birthday(entity.getBirthday())
                .gender(entity.getGender())
                .email(entity.getEmail())
                .phone(entity.getPhone())
                .city(entity.getCity().getName())
                .build();
        clientDto.add(linkTo(methodOn(ClientController.class).getClient(clientDto.getId())).withSelfRel(),
                linkTo(methodOn(CityController.class).getCity(entity.getCity().getId())).withRel("city")
        );
        return clientDto;
    }

    @Override
    public CollectionModel<ClientDto> toCollectionModel(Iterable<? extends ClientEntity> entities) {
        CollectionModel<ClientDto> clientDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(ClientController.class).getAllClients()).withSelfRel();
        clientDtos.add(selfLink);
        return clientDtos;
    }
}