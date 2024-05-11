package com.ievlev.faceit.controller;

import com.ievlev.faceit.dto.CuisineToShowDto;
import com.ievlev.faceit.dto.NewCuisineDto;
import com.ievlev.faceit.service.CuisineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@RequiredArgsConstructor
public class CuisineController {
    private final CuisineService cuisineService;

    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/api/v1/admin/cuisine")
    public CuisineToShowDto createCuisine(@RequestBody NewCuisineDto newCuisineDto) {
        return cuisineService.createNewCuisine(newCuisineDto);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping("/api/v1/admin/cuisine")
    public void deleteCuisine(@RequestParam @Min(1) long cuisineId) {
        cuisineService.deleteCuisine(cuisineId);
    }
}
