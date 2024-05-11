package com.ievlev.faceit.dto;

import com.ievlev.faceit.validation.constraint.NameSymbolsAreCorrectConstraint;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Jacksonized
@Builder
public class CreateNewMealDto {
    @NotNull
    @NotBlank
    @NameSymbolsAreCorrectConstraint
    private String name;
    private double price;
    private String mealType;
    private String cuisine;

    public CreateNewMealDto(String name, double price, String mealType, String cuisine) {
        this.name = name;
        this.price = price;
        this.mealType = mealType;
        this.cuisine = cuisine;
    }

    public CreateNewMealDto() {
    }
}
