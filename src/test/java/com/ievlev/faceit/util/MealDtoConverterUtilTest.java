package com.ievlev.faceit.util;

import com.ievlev.faceit.dto.MealToShowDto;
import com.ievlev.faceit.model.Cuisine;
import com.ievlev.faceit.model.Meal;
import com.ievlev.faceit.model.MealType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MealDtoConverterUtilTest {

    @Test
    public void testMealToShowDtoConvertFromMeal_WhenMealIsValid_ShouldReturnMealToShowDtoObject() {
        Cuisine cuisine = new Cuisine();
        cuisine.setName("Italian");
        Meal meal = new Meal();
        meal.setId(1L);
        meal.setName("Pizza Margherita");
        meal.setPrice(new BigDecimal("12.99"));
        meal.setMealType(MealType.MAIN);
        meal.setCuisine(cuisine);
        MealToShowDto mealToShowDto = MealDtoConverterUtil.MealToShowDtoConvertFromMeal(meal);
        assertNotNull(mealToShowDto);
        assertEquals(1L, mealToShowDto.getId());
        assertEquals("Pizza Margherita", mealToShowDto.getName());
        assertEquals("12.99", mealToShowDto.getPrice());
        assertEquals("MAIN", mealToShowDto.getMealType());
        assertEquals("Italian", mealToShowDto.getCuisine());
    }
}
