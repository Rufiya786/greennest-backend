package com.greennest.backend.service.impl;

import com.greennest.backend.entity.Category;
import com.greennest.backend.repository.CategoryRepository;
import com.greennest.backend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category addCategory(Category category) {

        if (categoryRepository.existsByName(category.getName())) {
            throw new RuntimeException("Category already exists: " + category.getName());
        }

        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
}
