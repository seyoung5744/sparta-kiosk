package com.example.challenge_lv1.repository;

import com.example.challenge_lv1.domain.Menu;
import com.example.challenge_lv1.domain.MenuItem;
import com.example.challenge_lv1.enums.Category;

import java.util.List;

public interface MenuRepository {

    void save(Menu menu);

    List<Menu> findAll();

    List<MenuItem> findByCategory(Category category);

    Menu findById(Long id);
}
