package com.ievlev.faceit.service;

import com.ievlev.faceit.dto.CreateNewMealDto;
import com.ievlev.faceit.dto.MealToShowDto;
import com.ievlev.faceit.exceptions.NameAlreadyExistsException;
import com.ievlev.faceit.model.Meal;
import com.ievlev.faceit.repository.MealRepository;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MealServiceTest {
    

    @Test
    public void testCreateNewMeal_WhenNameAlreadyExists() {
        CreateNewMealDto createNewMealDto = new CreateNewMealDto("Pizza", 10.0, "Main", "Italian");
        MealRepository mealRepository = mock(MealRepository.class);
        when(mealRepository.findByName("pizza")).thenReturn(Optional.of(new Meal()));
        CuisineService cuisineService = mock(CuisineService.class);
        MealService mealService = new MealService(mealRepository, cuisineService);
        assertThrows(NameAlreadyExistsException.class, () -> mealService.createNewMeal(createNewMealDto));
    }

    @Test
    public void testDeleteMealFromDB() {
        long mealId = 1L;
        MealRepository mealRepository = mock(MealRepository.class);
        CuisineService cuisineService = mock(CuisineService.class);
        MealService mealService = new MealService(mealRepository, cuisineService);
        mealService.deleteMealFromDB(mealId);
        verify(mealRepository, times(1)).deleteById(mealId);
    }

    @Test
    public void testFindById() {
        long mealId = 1L;
        Meal meal = new Meal();
        MealRepository mealRepository = mock(MealRepository.class);
        when(mealRepository.findById(mealId)).thenReturn(Optional.of(meal));
        CuisineService cuisineService = mock(CuisineService.class);
        MealService mealService = new MealService(mealRepository, cuisineService);
        Optional<Meal> result = mealService.findById(mealId);
        assertTrue(result.isPresent());
        assertEquals(meal, result.get());
    }

    @Test
    public void testShowAllMealsFromDB() {
        int pageNumber = 0;
        int pageSize = 10;
        Page<Meal> mealPage = new PageImpl<>(Collections.emptyList());
        MealRepository mealRepository = mock(MealRepository.class);
        when(mealRepository.findAll(PageRequest.of(pageNumber, pageSize))).thenReturn(mealPage);
        CuisineService cuisineService = mock(CuisineService.class);
        MealService mealService = new MealService(mealRepository, cuisineService);
        List<MealToShowDto> result = mealService.showAllMealsFromDB(pageNumber, pageSize);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
