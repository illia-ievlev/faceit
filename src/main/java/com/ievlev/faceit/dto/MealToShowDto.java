package com.ievlev.faceit.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
public class MealToShowDto {
    private long id;
    private String name;
    private String price;
    private String mealType;
    private String cuisine;

    public MealToShowDto(long id, String name, String price, String mealType, String cuisine) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.mealType = mealType;
        this.cuisine = cuisine;
    }

    public MealToShowDto() {
    }
}
