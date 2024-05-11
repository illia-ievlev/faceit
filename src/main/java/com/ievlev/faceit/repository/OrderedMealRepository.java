package com.ievlev.faceit.repository;

import com.ievlev.faceit.model.OrderedMeal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedMealRepository extends JpaRepository<OrderedMeal, Long> {
}
