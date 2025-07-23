package com.example.challenge.repository;

import com.example.challenge.domain.Menu;
import com.example.challenge.domain.MenuItem;
import com.example.challenge.enums.Category;

import java.util.List;

public interface MenuRepository {

    void save(Menu menu);

    List<Menu> findAll();

    List<MenuItem> findByCategory(Category category);

    Menu findById(Long id);
}
