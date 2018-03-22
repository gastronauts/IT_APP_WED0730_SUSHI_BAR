package com.mimosa.menu.impl;

import com.mimosa.menu.MenuService;
import com.mimosa.menu.model.Menu;
import com.mimosa.menu.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MenuImpl implements MenuService{

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public Optional<Menu> getMenuById(Integer menuId) {
        return Optional.ofNullable(menuRepository.findOne(menuId));
    }

    @Override
    public Menu addMenu(Menu menu) throws IllegalArgumentException {
        try {
            return menuRepository.save(menu);
        }
        catch (DataAccessException e){
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Menu modifyMenu(Menu menu) throws IllegalArgumentException {
        if(menuRepository.exists(menu.getId())){
            try {
                return menuRepository.save(menu);
            }
            catch (DataAccessException e){
                throw new IllegalArgumentException(e);
            }
        } else
            throw new IllegalArgumentException("Menu does not exist");
    }
}
