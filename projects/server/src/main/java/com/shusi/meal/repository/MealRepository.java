package com.shusi.meal.repository;

import com.shusi.meal.model.Meal;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MealRepository extends CrudRepository<Meal, Integer>{

    @Query("Select m" +
            " FROM Meal m" +
            " JOIN m.ingredients i" +
            " WHERE i.id = :ingredeint_id")
    List<Meal> getMealsByIngredient(@Param("ingredeint_id") Integer ingredeint_id);
}
