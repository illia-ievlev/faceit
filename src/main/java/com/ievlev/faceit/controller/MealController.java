package com.ievlev.faceit.controller;

import com.ievlev.faceit.dto.CreateNewMealDto;
import com.ievlev.faceit.dto.MealToShowDto;
import com.ievlev.faceit.service.MealService;
import com.ievlev.faceit.service.OrderedMealService;
import com.ievlev.faceit.util.UserIdFromAuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
public class MealController {
    private final MealService mealService;
    private final OrderedMealService orderedMealService;


    @ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping("/api/v1/secured/item")
    public void deleteMealFromOrder(Authentication authentication, @RequestParam @Min(1) long itemId) {
        long userId = UserIdFromAuthenticationUtil.getUserIdFromAuthentication(authentication);
        orderedMealService.deleteItemFromOrder(itemId, userId);
    }


    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping("/api/v1/admin/item")
    public MealToShowDto addMealToDB(@RequestBody CreateNewMealDto createNewMealDto) {
        return mealService.createNewMeal(createNewMealDto);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @DeleteMapping("/api/v1/admin/item")
    public void deleteMealFromDB(@RequestParam @Min(1) long itemId) {
        mealService.deleteMealFromDB(itemId);
    }

    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/api/v1/secured/item")
    public List<MealToShowDto> showAllMealsFromDb(@RequestParam @Min(0) int pageNumber, @RequestParam @Min(0) int pageSize) {
        return mealService.showAllMealsFromDB(pageNumber, pageSize);
    }

}
