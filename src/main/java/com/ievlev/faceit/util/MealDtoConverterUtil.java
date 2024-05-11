package com.ievlev.faceit.util;

import com.ievlev.faceit.dto.MealToShowDto;
import com.ievlev.faceit.model.Meal;

public class MealDtoConverterUtil {
    public static MealToShowDto MealToShowDtoConvertFromMeal(Meal meal) {
        return new MealToShowDto(meal.getId(),
                meal.getName(),
                meal.getPrice().toString(),
                meal.getMealType().toString(),
                meal.getCuisine().getName());
    }
}
