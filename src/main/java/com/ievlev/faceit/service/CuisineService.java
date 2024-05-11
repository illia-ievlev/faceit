package com.ievlev.faceit.service;

import com.ievlev.faceit.dto.CuisineToShowDto;
import com.ievlev.faceit.dto.NewCuisineDto;
import com.ievlev.faceit.exceptions.CuisineNotFoundException;
import com.ievlev.faceit.exceptions.NameAlreadyExistsException;
import com.ievlev.faceit.model.Cuisine;
import com.ievlev.faceit.repository.CuisineRepository;
import com.ievlev.faceit.util.CuisineDtoConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

@Service
@RequiredArgsConstructor
@Validated
public class CuisineService {
    private final CuisineRepository cuisineRepository;

    public void deleteCuisine(long cuisineId) {
        cuisineRepository.deleteById(cuisineId);
    }

    public CuisineToShowDto createNewCuisine(@Valid NewCuisineDto newCuisineDto) {
        String cuisineName = newCuisineDto.getName();
        if (cuisineRepository.findByName(cuisineName.trim().toLowerCase()).isPresent()) {
            throw new NameAlreadyExistsException("cuisine with name " + cuisineName + " already exists");
        }
        Cuisine cuisine = CuisineDtoConverter.convertNewCuisineDtoToCuisine(newCuisineDto);
        cuisineRepository.save(cuisine);
        return CuisineDtoConverter.cuisineToShowDtoFromCuisine(cuisine);
    }

    public Cuisine getByName(String name) {
        return cuisineRepository.findByName(name).orElseThrow(() -> new CuisineNotFoundException("there is no cuisine with that name: " + name));
    }

}
