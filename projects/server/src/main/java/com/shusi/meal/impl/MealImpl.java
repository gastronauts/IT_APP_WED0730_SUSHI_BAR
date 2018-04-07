package com.shusi.meal.impl;

import com.shusi.ingredient.model.Ingredient;
import com.shusi.ingredient.model.Presence;
import com.shusi.ingredient.repository.IngredientRepository;
import com.shusi.meal.MealService;
import com.shusi.meal.model.Meal;
import com.shusi.meal.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class MealImpl implements MealService{

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    @Override
    public Optional<Meal> getMealById(Integer mealId) {
        return Optional.ofNullable(mealRepository.findOne(mealId));
    }

    @Override
    public Collection<Meal> getAllMeals() {
        return (Collection<Meal>) mealRepository.findAll();
    }

    @Override
    public Meal addMeal(Meal meal) throws IllegalArgumentException {
        try{
            meal = checkIfPossibleToDo(meal);
            return mealRepository.save(meal);
        }
        catch (DataAccessException e){
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Meal modifyMeal(Meal meal) throws IllegalArgumentException {
        if(mealRepository.exists(meal.getId())){
            try {
                meal = checkIfPossibleToDo(meal);
                return mealRepository.save(meal);
            }
            catch (DataAccessException e){
                throw new IllegalArgumentException(e);
            }
        } else
            throw new IllegalArgumentException("Meal does not exist");
    }

    private Meal checkIfPossibleToDo(Meal meal){
        Collection<Ingredient> mealIngredients = meal.getIngredients();
        mealIngredients.forEach(currentIngredient -> currentIngredient = ingredientRepository.findOne(currentIngredient.getId()));
        if (mealIngredients.stream().anyMatch(ingredient -> ingredient.getQuantity().equals(Presence.EMPTY)))
            meal.setPossibleToDo(false);
        else
            meal.setPossibleToDo(true);
        return meal;
    }
}
