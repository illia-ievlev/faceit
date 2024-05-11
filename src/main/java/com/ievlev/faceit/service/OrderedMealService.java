package com.ievlev.faceit.service;

import com.ievlev.faceit.exceptions.MealNotFoundException;
import com.ievlev.faceit.model.OrderedMeal;
import com.ievlev.faceit.repository.OrderedMealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Min;

@Service
@RequiredArgsConstructor
public class OrderedMealService {
    private final OrderedMealRepository orderedMealRepository;

    public void deleteItemFromOrder(@Min(1) long itemId, @Min(1) long userId) {
        OrderedMeal orderedMeal = orderedMealRepository.findById(itemId).orElseThrow(() -> new MealNotFoundException("ordered meal with id: " + itemId + " not found"));
        if (!orderedMeal.getOrder().getUser().getId().equals(userId)) {
            throw new MealNotFoundException("you cannot delete an ordered meal that does not belong to this user");
        }
        orderedMealRepository.deleteById(itemId);
    }
}
