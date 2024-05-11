package com.ievlev.faceit.converter;

import com.ievlev.faceit.model.MealType;
import com.ievlev.faceit.util.MealTypeConverterUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class MealTypeConverterTest {

    @Test
    public void testConvertToDatabaseColumn() {
        MealType mealType = MealType.MAIN;
        MealTypeConverter converter = new MealTypeConverter();
        assertEquals("main", converter.convertToDatabaseColumn(mealType));
    }

    @Test
    public void testConvertToEntityAttribute() {
        String dbData = "main";
        MealTypeConverter converter = new MealTypeConverter();
        when(MealTypeConverterUtil.convertStringToEntityAttribute(dbData)).thenReturn(MealType.MAIN);
        assertEquals(MealType.MAIN, converter.convertToEntityAttribute(dbData));
    }
}