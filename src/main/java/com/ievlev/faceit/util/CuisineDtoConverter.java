package com.ievlev.faceit.util;

import com.ievlev.faceit.dto.CuisineToShowDto;
import com.ievlev.faceit.dto.NewCuisineDto;
import com.ievlev.faceit.model.Cuisine;


public class CuisineDtoConverter {

    public static Cuisine convertNewCuisineDtoToCuisine(NewCuisineDto newCuisineDto) {
        return new Cuisine(newCuisineDto.getName().trim().toLowerCase());
    }

    public static CuisineToShowDto cuisineToShowDtoFromCuisine(Cuisine cuisine) {
        return new CuisineToShowDto(cuisine.getId(), cuisine.getName());
    }
}
