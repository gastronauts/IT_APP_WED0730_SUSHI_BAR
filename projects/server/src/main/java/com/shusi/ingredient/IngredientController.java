package com.shusi.ingredient;

import com.shusi.ingredient.model.Ingredient;
import com.shusi.ingredient.model.Presence;
import com.shusi.utilities.MergeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/ingredient")
public class IngredientController {

    @Autowired
    public IngredientService ingredientService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Ingredient>> getMeal(){
        return ResponseEntity.ok(ingredientService.getAllIngredients());
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Ingredient> modifyIngredient(@RequestBody Ingredient ingredient)
            throws InstantiationException, IllegalAccessException{
        Optional<Ingredient> originalIngredient = ingredientService.getIngredient(ingredient.getId());
        if(!originalIngredient.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Ingredient finalIngredient = MergeTool.mergeObjects(ingredient,originalIngredient.get());
        try {
            return new ResponseEntity<>(ingredientService.modifyIngredient(finalIngredient),HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Ingredient> addIngredient(@RequestBody Ingredient ingredient){
        try {
            if(ingredient.getQuantity() == null)
                ingredient.setQuantity(Presence.EMPTY);
            return new ResponseEntity<>(ingredientService.addIngredient(ingredient),HttpStatus.OK);
        }
        catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}
