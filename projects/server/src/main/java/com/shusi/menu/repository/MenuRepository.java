package com.shusi.menu.repository;

import com.shusi.menu.model.Menu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MenuRepository extends CrudRepository<Menu,Integer>{

    @Query("Select m" +
            " FROM Menu m" +
            " WHERE m.current = true ")
    Optional<Menu> getCurrentMenu();
}
