package com.mimosa.ingredient;

import com.mimosa.ingredient.model.Ingredient;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Optional;

@Service
public interface IngredientService {

    Optional<Ingredient> getIngredient(Integer ingredientId);

    Ingredient addIngredient(Ingredient ingredient) throws IllegalArgumentException;

    Ingredient modifyIngredient(Ingredient ingredient) throws IllegalArgumentException;

}
