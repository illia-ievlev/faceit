package com.ievlev.faceit.util;

import com.ievlev.faceit.exceptions.MealTypeNotFoundException;
import com.ievlev.faceit.model.MealType;

public class MealTypeConverterUtil {

    public static MealType convertStringToEntityAttribute(String data) {
        return MealType.fromName(data).orElseThrow(() -> new MealTypeNotFoundException("mealType: " + data
                + "not found in java enum MealType"));
    }
}
