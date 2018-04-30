package com.shusi.meal;

import com.shusi.meal.model.Meal;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public interface MealService {

    Optional<Meal> getMealById(Integer mealId);

    Collection<Meal> getAllMeals();

    Meal addMeal(Meal meal) throws IllegalArgumentException;

    Meal modifyMeal(Meal meal) throws IllegalArgumentException;

    Meal deleteIngredientsFormMeal (Meal meal, Collection<Integer> ingredients) throws IllegalArgumentException;
}
