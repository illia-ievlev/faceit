package com.ievlev.faceit.util;

import com.ievlev.faceit.dto.CuisineToShowDto;
import com.ievlev.faceit.dto.NewCuisineDto;
import com.ievlev.faceit.model.Cuisine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CuisineDtoConverterTest {

    @Test
    public void testConvertNewCuisineDtoToCuisine_WhenNewCuisineDtoIsValid_ShouldReturnCuisineObject() {
        NewCuisineDto newCuisineDto = new NewCuisineDto();
        newCuisineDto.setName("Italian");
        Cuisine cuisine = CuisineDtoConverter.convertNewCuisineDtoToCuisine(newCuisineDto);
        assertNotNull(cuisine);
        assertEquals("italian", cuisine.getName());
    }

    @Test
    public void testCuisineToShowDtoFromCuisine_WhenCuisineIsValid_ShouldReturnCuisineToShowDtoObject() {
        Cuisine cuisine = new Cuisine();
        cuisine.setId(1L);
        cuisine.setName("Mexican");
        CuisineToShowDto cuisineToShowDto = CuisineDtoConverter.cuisineToShowDtoFromCuisine(cuisine);
        assertNotNull(cuisineToShowDto);
        assertEquals(1L, cuisineToShowDto.getId());
        assertEquals("Mexican", cuisineToShowDto.getName());
    }
}
