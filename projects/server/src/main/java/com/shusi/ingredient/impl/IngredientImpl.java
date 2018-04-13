package com.shusi.ingredient.impl;

import com.shusi.ingredient.IngredientService;
import com.shusi.ingredient.model.Ingredient;
import com.shusi.ingredient.model.Presence;
import com.shusi.ingredient.repository.IngredientRepository;
import com.shusi.meal.model.Meal;
import com.shusi.meal.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
public class IngredientImpl implements IngredientService{

    @Autowired
    private IngredientRepository ingredientRepository;

    @Autowired
    private MealRepository mealRepository;

    @Override
    public Collection<Ingredient> getAllIngredients() {
        return (Collection<Ingredient>) ingredientRepository.findAll();
    }

    @Override
    public Optional<Ingredient> getIngredient(Integer ingredientId) {
        return Optional.ofNullable(ingredientRepository.findOne(ingredientId));
    }

    @Override
    public Ingredient addIngredient(Ingredient ingredient) throws IllegalArgumentException {
        try{
            return ingredientRepository.save(ingredient);
        }
        catch (DataAccessException e){
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Ingredient modifyIngredient(Ingredient ingredient) throws IllegalArgumentException {
        if(ingredientRepository.exists(ingredient.getId())){
            try {
                Ingredient newIngredient = ingredientRepository.save(ingredient);
                List<Meal> mealsWithIngredient = mealRepository.getMealsByIngredient(newIngredient.getId());
                List<Meal> changedMeals = new ArrayList<Meal>();
                mealsWithIngredient.forEach(meal -> {
                    boolean currentPossibleToDo = meal.isPossibleToDo();
                    if (meal.getIngredients().stream().anyMatch( currentIngredient -> currentIngredient.getQuantity().equals(Presence.EMPTY)))
                        meal.setPossibleToDo(false);
                    else
                        meal.setPossibleToDo(true);
                    if (meal.isPossibleToDo() != currentPossibleToDo){
                        changedMeals.add(meal);
                    }
                });
                mealRepository.save(changedMeals);
                return newIngredient;
            }
            catch (DataAccessException e){
                throw new IllegalArgumentException(e);
            }
        } else
            throw new IllegalArgumentException("Ingredient does not exist");
    }
}
