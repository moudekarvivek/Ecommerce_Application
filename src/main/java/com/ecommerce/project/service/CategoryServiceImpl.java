package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService{
    private List<Category> categories = new ArrayList<>();
    private  Long nextId = 1L; // Used to get the ID automatically
    @Override
    public List<Category> getAllCategories() {
        return categories;
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categories.add(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        Category category = categories.stream()  // Making Categories list into stream
                .filter(c -> c.getCategoryId().equals(categoryId)) //we are doing Functional Programming here, and checking the categoryid matches the category we have
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found"));
        if(category == null)
            return "Category Not Found";
        categories.remove(category);
        return "Category With categoryId: " + categoryId + " deleted Successfully!!";
    }
}
