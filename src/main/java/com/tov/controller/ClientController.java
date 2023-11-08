package com.tov.controller;

import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.tov.domain.ClientEntity;
import com.tov.dto.ClientDto;
import com.tov.dto.assembler.ClientDtoAssembler;
import com.tov.service.ClientService;

import java.util.List;

@RestController
@RequestMapping(value = "api/clients")
public class ClientController {
    private final ClientService clientService;
    private final ClientDtoAssembler clientDtoAssembler;

    public ClientController(ClientService clientService, ClientDtoAssembler clientDtoAssembler) {
        this.clientService = clientService;
        this.clientDtoAssembler = clientDtoAssembler;
    }

    @GetMapping(value = "/{clientId}")
    public ResponseEntity<ClientDto> getClient(@PathVariable Integer clientId) {
        ClientEntity clientEntity = clientService.findById(clientId);
        ClientDto clientDto = clientDtoAssembler.toModel(clientEntity);
        return new ResponseEntity<>(clientDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<ClientDto>> getAllClients() {
        List<ClientEntity> entityList = clientService.findAll();
        CollectionModel<ClientDto> dtos = clientDtoAssembler.toCollectionModel(entityList);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDto> addClient(@RequestBody ClientEntity entity) {
        ClientEntity newEntity = clientService.create(entity);
        ClientDto dto = clientDtoAssembler.toModel(newEntity);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{clientId}")
    public ResponseEntity<?> updateClient(@RequestBody ClientEntity entity, @PathVariable Integer clientId) {
        clientService.update(clientId, entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{clientId}")
    public ResponseEntity<?> deleteClient(@PathVariable Integer clientId) {
        clientService.delete(clientId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping(value = "/withCityId/{vehicleTypeId}")
    public ResponseEntity<CollectionModel<ClientDto>> getAllWithCityId(@PathVariable Integer cityId) {
        List<ClientEntity> entityList = clientService.getClientEntitiesByCityId(cityId);
        CollectionModel<ClientDto> dtos = clientDtoAssembler.toCollectionModel(entityList);
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}