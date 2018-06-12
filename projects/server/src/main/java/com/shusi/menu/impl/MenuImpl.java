package com.shusi.menu.impl;

import com.shusi.menu.MenuService;
import com.shusi.menu.model.Menu;
import com.shusi.menu.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.util.Collection;
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
    public Collection<Menu> getAllMenus() {
        return (Collection<Menu>) menuRepository.findAll();
    }

    @Override
    public Optional<Menu> getCurrentMenu() {
        return menuRepository.getCurrentMenu();
    }

    @Override
    public Menu addMenu(Menu menu) throws IllegalArgumentException {
        try {
            changeCurrentMenu(menu);
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
                changeCurrentMenu(menu);
                return menuRepository.save(menu);
            }
            catch (DataAccessException e){
                throw new IllegalArgumentException(e);
            }
        } else
            throw new IllegalArgumentException("Menu does not exist");
    }

    private void changeCurrentMenu(Menu newMenu){
        if (newMenu.isCurrent()) {
            Optional<Menu> optionalMenu = menuRepository.getCurrentMenu();
            if (optionalMenu.isPresent() && !optionalMenu.get().getId().equals(newMenu.getId())) {
                Menu currentMenu = optionalMenu.get();
                currentMenu.setCurrent(false);
                menuRepository.save(currentMenu);
            }
        }
    }
}
