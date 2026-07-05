package com.javacart.service;

import java.util.List;

import com.javacart.dao.CategoryDAO;
import com.javacart.model.Category;

public class CategoryService {

    private CategoryDAO dao = new CategoryDAO();

    public boolean addCategory(Category category) {
        return dao.addCategory(category);
    }

    public List<Category> getAllCategories() {
        return dao.getAllCategories();
    }

    public boolean deleteCategory(int categoryId) {
        return dao.deleteCategory(categoryId);
    }

}