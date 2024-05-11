package com.ievlev.faceit.controller;
import com.ievlev.faceit.dto.CuisineToShowDto;
import com.ievlev.faceit.dto.NewCuisineDto;
import com.ievlev.faceit.service.CuisineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CuisineControllerTest {

    @InjectMocks
    private CuisineController cuisineController;

    @Mock
    private CuisineService cuisineService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateCuisine() {
        NewCuisineDto newCuisineDto = new NewCuisineDto();
        newCuisineDto.setName("Test Cuisine");
        CuisineToShowDto cuisineToShowDto = new CuisineToShowDto();
        cuisineToShowDto.setId(1L);
        cuisineToShowDto.setName("Test Cuisine");
        when(cuisineService.createNewCuisine(newCuisineDto)).thenReturn(cuisineToShowDto);
        CuisineToShowDto result = cuisineController.createCuisine(newCuisineDto);
        assertEquals(cuisineToShowDto, result);
        verify(cuisineService, times(1)).createNewCuisine(newCuisineDto);
    }

    @Test
    public void testDeleteCuisine() {
        cuisineController.deleteCuisine(1L);
        verify(cuisineService, times(1)).deleteCuisine(1L);
    }
}