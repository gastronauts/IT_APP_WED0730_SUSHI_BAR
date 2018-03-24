package com.shusi.meal.impl;

import com.shusi.meal.MealService;
import com.shusi.meal.model.Meal;
import com.shusi.meal.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MealImpl implements MealService{

    @Autowired
    private MealRepository mealRepository;

    @Override
    public Optional<Meal> getMealById(Integer mealId) {
        return Optional.ofNullable(mealRepository.findOne(mealId));
    }

    @Override
    public Meal addMeal(Meal meal) throws IllegalArgumentException {
        try{
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
                return mealRepository.save(meal);
            }
            catch (DataAccessException e){
                throw new IllegalArgumentException(e);
            }
        } else
            throw new IllegalArgumentException("Meal does not exist");
    }
}
