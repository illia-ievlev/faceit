package com.ievlev.faceit.dto;

import lombok.Data;

@Data
public class OrderedMealToShowDto {
    private String mealName;
    private String mealPrice;
    private String mealType;
    private String cuisine;
    private String addLemon;
    private String addIce;

    public OrderedMealToShowDto(String mealName, String mealPrice, String mealType, String cuisine, String addLemon, String addIce) {
        this.mealName = mealName;
        this.mealPrice = mealPrice;
        this.mealType = mealType;
        this.cuisine = cuisine;
        this.addLemon = addLemon;
        this.addIce = addIce;
    }

    public OrderedMealToShowDto() {
    }
}
