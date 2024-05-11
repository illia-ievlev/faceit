package com.ievlev.faceit.controller;

import com.ievlev.faceit.dto.CreateNewMealDto;
import com.ievlev.faceit.dto.MealToShowDto;
import com.ievlev.faceit.service.MealService;
import com.ievlev.faceit.service.OrderedMealServiceTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MealControllerTest {

    @InjectMocks
    private MealController mealController;

    @Mock
    private MealService mealService;

    @Mock
    private OrderedMealServiceTest orderedMealService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddMealToDB() {
        CreateNewMealDto createNewMealDto = new CreateNewMealDto();
        createNewMealDto.setName("Test Meal");
        MealToShowDto expectedMealDto = new MealToShowDto();
        expectedMealDto.setName("Test Meal");
        when(mealService.createNewMeal(createNewMealDto)).thenReturn(expectedMealDto);
        MealToShowDto result = mealController.addMealToDB(createNewMealDto);
        assertEquals(expectedMealDto, result);
    }

    @Test
    public void testDeleteMealFromDB() {
        mealController.deleteMealFromDB(1L);
        verify(mealService, times(1)).deleteMealFromDB(1L);
    }

    @Test
    public void testShowAllMealsFromDb() {
        List<MealToShowDto> expectedMealDtoList = new ArrayList<>();
        when(mealService.showAllMealsFromDB(0, 10)).thenReturn(expectedMealDtoList);
        List<MealToShowDto> result = mealController.showAllMealsFromDb(0, 10);
        assertEquals(expectedMealDtoList, result);
    }
}