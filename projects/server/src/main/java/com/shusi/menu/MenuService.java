package com.shusi.menu;

import com.shusi.menu.model.Menu;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface MenuService {

    Optional<Menu> getMenuById(Integer menuId);

    Menu addMenu(Menu menu) throws IllegalArgumentException;

    Menu modifyMenu(Menu menu) throws IllegalArgumentException;
}
