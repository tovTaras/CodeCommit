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
@Relation(itemRelation = "delivery", collectionRelation = "deliveries")
public class DeliveryDto extends RepresentationModel<DeliveryDto> {
    private Integer id;
    private String deliveryPerson;
    private Integer apartmentNumber;
    private String status;
    private String deliveryTimeAprox;
    private String deliveryTimeReal;
    private Float deliveryPayment;
}