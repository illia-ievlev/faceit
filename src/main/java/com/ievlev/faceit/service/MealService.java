package com.ievlev.faceit.service;

import com.ievlev.faceit.dto.CreateNewMealDto;
import com.ievlev.faceit.dto.MealToShowDto;
import com.ievlev.faceit.exceptions.NameAlreadyExistsException;
import com.ievlev.faceit.model.Meal;
import com.ievlev.faceit.repository.MealRepository;
import com.ievlev.faceit.util.MealDtoConverterUtil;
import com.ievlev.faceit.util.MealTypeConverterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Validated
public class MealService {
    private final MealRepository mealRepository;
    private final CuisineService cuisineService;

    public MealToShowDto createNewMeal(@Valid CreateNewMealDto createNewMealDto) {
        if (mealRepository.findByName(createNewMealDto.getName().trim().toLowerCase()).isPresent()) {
            throw new NameAlreadyExistsException("meal name already exists");
        }
        Meal meal = new Meal(
                createNewMealDto.getName().trim().toLowerCase(),
                BigDecimal.valueOf(createNewMealDto.getPrice()),
                MealTypeConverterUtil.convertStringToEntityAttribute(createNewMealDto.getMealType()),
                cuisineService.getByName(createNewMealDto.getCuisine())
        );
        mealRepository.save(meal);
        return MealDtoConverterUtil.MealToShowDtoConvertFromMeal(meal);
    }

    public void deleteMealFromDB(long itemId) {
        mealRepository.deleteById(itemId);
    }

    public Optional<Meal> findById(long mealId) {
        return mealRepository.findById(mealId);
    }

    public List<MealToShowDto> showAllMealsFromDB(int pageNumber, int pageSize) {
        Page<Meal> meals = mealRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return meals.get()
                .map(MealDtoConverterUtil::MealToShowDtoConvertFromMeal)
                .collect(Collectors.toList());
    }
}
