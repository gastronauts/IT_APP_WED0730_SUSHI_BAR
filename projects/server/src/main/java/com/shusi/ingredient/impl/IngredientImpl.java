package com.shusi.ingredient.impl;

import com.shusi.ingredient.IngredientService;
import com.shusi.ingredient.model.Ingredient;
import com.shusi.ingredient.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Optional;

@Component
public class IngredientImpl implements IngredientService{

    @Autowired
    private IngredientRepository ingredientRepository;

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
                return ingredientRepository.save(ingredient);
            }
            catch (DataAccessException e){
                throw new IllegalArgumentException(e);
            }
        } else
            throw new IllegalArgumentException("Ingredient does not exist");
    }
}
