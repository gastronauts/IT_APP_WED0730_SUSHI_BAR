package com.shusi.menu;

import com.shusi.menu.model.Menu;
import com.shusi.utilities.MergeTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/menu")
public class MenuController {

    @Autowired
    public MenuService menuService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Collection<Menu>> getAllMenu(){
        return ResponseEntity.ok(menuService.getAllMenus());
    }


    @RequestMapping(value = "current",method = RequestMethod.GET)
    public ResponseEntity<Menu> getCurrentMenu(){
        return menuService.getCurrentMenu().map(u -> new ResponseEntity<>(u, HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    public ResponseEntity<Menu> getMenu(@PathVariable Integer id){
        return menuService.getMenuById(id).map(u -> new ResponseEntity<>(u, HttpStatus.OK)).
                orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Menu> addMenu(@RequestBody Menu menu){
        try {
            return new ResponseEntity<>(menuService.addMenu(menu),HttpStatus.OK);
        }
        catch (IllegalArgumentException e){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Menu> modifyMenu(@RequestBody Menu meal)
            throws InstantiationException, IllegalAccessException{
        Optional<Menu> originalMeal = menuService.getMenuById(meal.getId());
        if(!originalMeal.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Menu finalMeal = MergeTool.mergeObjects(meal,originalMeal.get());
        try {
            return new ResponseEntity<>(menuService.modifyMenu(finalMeal),HttpStatus.OK);
        }
        catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
