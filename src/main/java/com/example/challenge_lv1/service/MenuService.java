package com.example.challenge_lv1.service;

import com.example.challenge_lv1.domain.Menu;
import com.example.challenge_lv1.domain.MenuItem;
import com.example.challenge_lv1.enums.Category;
import com.example.challenge_lv1.repository.MenuRepository;

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
