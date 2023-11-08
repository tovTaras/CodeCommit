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
@Relation(itemRelation = "order", collectionRelation = "orders")
public class OrderDto extends RepresentationModel<OrderDto> {
    private Integer id;
    private String office;
    private String city;
    private String client;
    private String delivery;
    private String time;
    private Float totalPrice;
    private String adressee;
}