package com.mimosa.meal;

import com.mimosa.meal.model.Meal;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface MealService {

    Optional<Meal> getMealById(Integer mealId);

    Meal addMeal(Meal meal) throws IllegalArgumentException;

    Meal modifyMeal(Meal meal) throws IllegalArgumentException;
}