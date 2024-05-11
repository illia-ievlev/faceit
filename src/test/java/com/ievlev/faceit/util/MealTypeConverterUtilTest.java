package com.ievlev.faceit.util;
import com.ievlev.faceit.exceptions.MealTypeNotFoundException;
import com.ievlev.faceit.model.MealType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MealTypeConverterUtilTest {

    @Test
    public void testConvertStringToEntityAttribute_WhenValidData_ShouldReturnCorrectMealType() {
        String validData = "main";
        MealType mealType = MealTypeConverterUtil.convertStringToEntityAttribute(validData);
        assertEquals(MealType.MAIN, mealType);
    }

    @Test
    public void testConvertStringToEntityAttribute_WhenInvalidData_ShouldThrowException() {
        // Arrange
        String invalidData = "DESSERT";

        // Act & Assert
        assertThrows(MealTypeNotFoundException.class, () -> {
            MealTypeConverterUtil.convertStringToEntityAttribute(invalidData);
        });
    }
}
