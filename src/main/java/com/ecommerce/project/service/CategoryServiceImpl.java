package com.ecommerce.project.service;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{
    //private List<Category> categories = new ArrayList<>();
    private  Long nextId = 1L; // Used to get the ID automatically
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(nextId++);
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(Long categoryId) {
        List<Category> categories = categoryRepository.findAll();
        Category category = categories.stream()  // Making Categories list into stream
                .filter(c -> c.getCategoryId().equals(categoryId)) //we are doing Functional Programming here, and checking the categoryid matches the category we have
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found"));
        if(category == null)
            return "Category Not Found";
        categoryRepository.delete(category);
        return "Category With categoryId: " + categoryId + " deleted Successfully!!";
    }

    @Override
    public Category updateCategory(Category category, Long categoryId) {
        List<Category> categories = categoryRepository.findAll();

        Optional<Category> optionalCategory = categories.stream()  // Making Categories list into stream
                .filter(c -> c.getCategoryId().equals(categoryId)) //we are doing Functional Programming here, and checking the categoryid matches the category we have
                .findFirst();

        if (optionalCategory.isPresent()){
            Category existingCategory = optionalCategory.get();
            existingCategory.setCategoryName(category.getCategoryName());
            Category savedCategory  = categoryRepository.save(existingCategory);
            return savedCategory;
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Category Not Found");
        }

    }
}
