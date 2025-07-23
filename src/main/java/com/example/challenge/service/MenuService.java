package com.example.challenge.service;

import com.example.challenge.domain.Menu;
import com.example.challenge.domain.MenuItem;
import com.example.challenge.enums.Category;
import com.example.challenge.repository.MenuRepository;

import java.util.List;

public class MenuService {

    private final MenuRepository menuRepository;

    public MenuService(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    public void save(Menu menu) {
        menuRepository.save(menu);
    }

    public Menu findById(Long id) {
        return menuRepository.findById(id);
    }

    public List<Menu> findAllMenu() {
        return menuRepository.findAll();
    }

    public List<MenuItem> findAllMenuItemByCategory(Category category) {
        return menuRepository.findByCategory(category);
    }

}
