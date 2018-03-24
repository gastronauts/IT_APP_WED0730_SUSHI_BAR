package com.shusi.meal;

import com.shusi.meal.model.Meal;
import com.shusi.utilities.MergeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/meal")
public class MealController {

    @Autowired
    public MealService mealService;

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<Meal> getMeal(@PathVariable Integer id){
        return mealService.getMealById(id).map(u -> new ResponseEntity<>(u,HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Meal> addMeal(@RequestBody Meal meal){
        try {
            return new ResponseEntity<>(meal = mealService.addMeal(meal),HttpStatus.OK);
        }
        catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Meal> modifyMeal(@RequestBody Meal meal)
            throws InstantiationException, IllegalAccessException{
        Optional<Meal> originalMeal = mealService.getMealById(meal.getId());
        if(!originalMeal.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Meal finalMeal = MergeTool.mergeObjects(meal,originalMeal.get());
        try {
            return new ResponseEntity<>(mealService.modifyMeal(finalMeal),HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
