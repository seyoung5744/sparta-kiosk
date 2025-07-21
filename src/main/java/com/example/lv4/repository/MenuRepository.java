package com.example.lv4.repository;

import com.example.lv4.domain.Menu;
import com.example.lv4.domain.MenuItem;
import com.example.lv4.enums.Category;

import java.util.List;

public interface MenuRepository {

    void save(Menu menu);

    List<Menu> findAll();

    List<MenuItem> findByCategory(Category category);

    Menu findById(Long id);
}
