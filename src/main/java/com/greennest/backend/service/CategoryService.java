package com.greennest.backend.service;
import java.util.List;

import com.greennest.backend.entity.Category;
public interface CategoryService {
	

    Category addCategory(Category category);

    List<Category> getAllCategories();

}
