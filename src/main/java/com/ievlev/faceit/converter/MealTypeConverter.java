package com.ievlev.faceit.converter;

import com.ievlev.faceit.model.MealType;
import com.ievlev.faceit.util.MealTypeConverterUtil;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


@Converter
public class MealTypeConverter implements AttributeConverter<MealType, String> {
    @Override
    public String convertToDatabaseColumn(MealType attribute) {
        return attribute.getName();
    }

    @Override
    public MealType convertToEntityAttribute(String dbData) {
        return MealTypeConverterUtil.convertStringToEntityAttribute(dbData);
    }
}
