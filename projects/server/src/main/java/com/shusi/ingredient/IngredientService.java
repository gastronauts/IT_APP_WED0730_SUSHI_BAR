package com.shusi.ingredient;

import com.shusi.ingredient.model.Ingredient;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IngredientService {

    Optional<Ingredient> getIngredient(Integer ingredientId);

    Ingredient addIngredient(Ingredient ingredient) throws IllegalArgumentException;

    Ingredient modifyIngredient(Ingredient ingredient) throws IllegalArgumentException;

}
