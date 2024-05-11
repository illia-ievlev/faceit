package com.ievlev.faceit.util;

import com.ievlev.faceit.dto.OrderedMealToShowDto;
import com.ievlev.faceit.model.OrderedMeal;

public class OrderedMealDtoConverter {
    public static OrderedMealToShowDto orderedMealDtoToShowFromOrderedMeal(OrderedMeal orderedMeal) {
        return new OrderedMealToShowDto(orderedMeal.getMeal().getName(),
                orderedMeal.getMeal().getPrice().toString(),
                orderedMeal.getMeal().getMealType().toString(),
                orderedMeal.getMeal().getCuisine().getName(),
                String.valueOf(orderedMeal.isAddLemon()),
                String.valueOf(orderedMeal.isAddIce()));
    }
}
