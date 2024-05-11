package com.ievlev.faceit.util;

import com.ievlev.faceit.dto.OrderedMealToShowDto;
import com.ievlev.faceit.model.Cuisine;
import com.ievlev.faceit.model.Meal;
import com.ievlev.faceit.model.MealType;
import com.ievlev.faceit.model.OrderedMeal;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderedMealDtoConverterTest {

    @Test
    public void testOrderedMealDtoToShowFromOrderedMeal_WhenValidOrderedMeal_ShouldReturnCorrectDto() {
        Meal meal = new Meal("Spaghetti", BigDecimal.ONE, MealType.MAIN, new Cuisine("Italian"));
        OrderedMeal orderedMeal = new OrderedMeal(meal, false, false, null);
        OrderedMealToShowDto dto = OrderedMealDtoConverter.orderedMealDtoToShowFromOrderedMeal(orderedMeal);
        assertEquals("Spaghetti", dto.getMealName());
        assertEquals("MAIN", dto.getMealType());
        assertEquals("Italian", dto.getCuisine());
        assertEquals("false", dto.getAddLemon());
        assertEquals("false", dto.getAddIce());
    }

}
