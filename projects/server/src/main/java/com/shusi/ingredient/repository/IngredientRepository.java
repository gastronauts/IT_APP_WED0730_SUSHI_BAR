package com.shusi.ingredient.repository;

import com.shusi.ingredient.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient,Integer>{
}
