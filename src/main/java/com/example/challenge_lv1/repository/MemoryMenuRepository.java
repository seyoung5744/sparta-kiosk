package com.example.challenge_lv1.repository;

import com.example.challenge_lv1.domain.Menu;
import com.example.challenge_lv1.domain.MenuItem;
import com.example.challenge_lv1.enums.Category;

import java.util.*;
import java.util.stream.Collectors;

public class MemoryMenuRepository implements MenuRepository {

    private static final Map<Long, Menu> store = new HashMap<>();

    @Override
    public void save(Menu menu) {
        store.put(menu.getId(), menu);
    }

    @Override
    public List<Menu> findAll() {
        return store.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .map(Map.Entry::getValue)
                .toList();
    }

    @Override
    public List<MenuItem> findByCategory(Category category) {
        Map<Category, List<MenuItem>> sortedMenuItemByCategory = store.values().stream()
                .flatMap(menu -> menu.getMenuItems().stream())
                .sorted(Comparator.comparing(MenuItem::getId))
                .collect(Collectors.groupingBy(
                        MenuItem::getCategory,
                        LinkedHashMap::new,
                        Collectors.toList()
                ));
        return sortedMenuItemByCategory.getOrDefault(category, Collections.emptyList());
    }

    @Override
    public Menu findById(Long id) {
        return store.get(id);
    }
}
