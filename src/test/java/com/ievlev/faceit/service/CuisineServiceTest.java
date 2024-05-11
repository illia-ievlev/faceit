package com.ievlev.faceit.service;

import com.ievlev.faceit.dto.NewCuisineDto;
import com.ievlev.faceit.exceptions.CuisineNotFoundException;
import com.ievlev.faceit.exceptions.NameAlreadyExistsException;
import com.ievlev.faceit.model.Cuisine;
import com.ievlev.faceit.repository.CuisineRepository;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CuisineServiceTest {

    @Test
    public void testDeleteCuisine() {
        long cuisineId = 1L;
        CuisineRepository cuisineRepository = mock(CuisineRepository.class);
        CuisineService cuisineService = new CuisineService(cuisineRepository);
        cuisineService.deleteCuisine(cuisineId);
        verify(cuisineRepository, times(1)).deleteById(cuisineId);
    }

    @Test
    public void testCreateNewCuisine_WhenNameAlreadyExists() {
        NewCuisineDto newCuisineDto = new NewCuisineDto("Italian");
        CuisineRepository cuisineRepository = mock(CuisineRepository.class);
        when(cuisineRepository.findByName("italian")).thenReturn(Optional.of(new Cuisine()));
        CuisineService cuisineService = new CuisineService(cuisineRepository);
        assertThrows(NameAlreadyExistsException.class, () -> cuisineService.createNewCuisine(newCuisineDto));
    }

    @Test
    public void testGetByName_WhenCuisineNotFound() {
        String cuisineName = "Italian";
        CuisineRepository cuisineRepository = mock(CuisineRepository.class);
        when(cuisineRepository.findByName(cuisineName)).thenReturn(Optional.empty());
        CuisineService cuisineService = new CuisineService(cuisineRepository);
        assertThrows(CuisineNotFoundException.class, () -> cuisineService.getByName(cuisineName));
    }
}
