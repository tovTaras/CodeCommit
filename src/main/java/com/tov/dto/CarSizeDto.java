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
@Relation(itemRelation = "carSize", collectionRelation = "carSizes")
public class CarSizeDto extends RepresentationModel<CarSizeDto> {
    private Integer id;
    private String diameter;
    private Float ingredientCostCoef;
    private String name;
    private String price;
}