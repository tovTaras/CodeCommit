package com.tov.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;


@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "deliveryPerson", collectionRelation = "deliveryPersons")
public class DeliveryPersonDto extends RepresentationModel<DeliveryPersonDto> {
    private Integer id;
    private Float latitude;
    private Float longitude;
    private String surname;
    private String name;
    private String phone;
    private String adress;
}