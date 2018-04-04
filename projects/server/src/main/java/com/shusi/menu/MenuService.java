package com.shusi.menu;

import com.shusi.menu.model.Menu;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.Optional;

@Service
public interface MenuService {

    Optional<Menu> getMenuById(Integer menuId);

    Collection<Menu> getAllMenus();

    Optional<Menu> getCurrentMenu();

    Menu addMenu(Menu menu) throws IllegalArgumentException;

    Menu modifyMenu(Menu menu) throws IllegalArgumentException;
}
