package com.mimosa.meal.repository;

import com.mimosa.meal.model.Meal;
import org.springframework.data.repository.CrudRepository;

public interface MealRepository extends CrudRepository<Meal, Integer>{
}
