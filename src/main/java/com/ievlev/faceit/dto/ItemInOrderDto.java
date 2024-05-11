package com.ievlev.faceit.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.Min;

@Data
@Jacksonized
@Builder
@AllArgsConstructor
public class ItemInOrderDto {

    @Min(1)
    private long mealId;

    private boolean addIce;

    private boolean addLemon;
}
