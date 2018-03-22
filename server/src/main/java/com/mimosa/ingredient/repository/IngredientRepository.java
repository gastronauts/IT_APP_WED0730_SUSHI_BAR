package com.mimosa.ingredient.repository;

import com.mimosa.ingredient.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient,Integer>{
}
