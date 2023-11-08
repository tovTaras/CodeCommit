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
@Relation(itemRelation = "office", collectionRelation = "offices")
public class OfficeDto extends RepresentationModel<OfficeDto> {
    private Integer id;
    private String streetAddress;
    private String phone;
    private String officeHead;
    private String city;
}