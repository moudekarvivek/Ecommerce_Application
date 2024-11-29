package com.ecommerce.project.controller;

import com.ecommerce.project.model.Category;
import com.ecommerce.project.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {

    private CategoryService categoryService;

    // Constructor injection
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/api/public/categories")
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories,HttpStatus.OK);
    }
    @PostMapping("/api/public/categories")
    public ResponseEntity<String> createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
        return new ResponseEntity<>("Category Added Successfully", HttpStatus.CREATED);
    }

    @DeleteMapping("/api/admin/categories/{categoryId}") // Because Id is coming here through pathvariable
    public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
      try{
          String status = categoryService.deleteCategory(categoryId);
          //return new ResponseEntity<>(status, HttpStatus.OK);
          //return ResponseEntity.ok(status);
          return ResponseEntity.status(HttpStatus.OK).body(status);
      }catch (ResponseStatusException e){
          return new ResponseEntity<>(e.getReason(), e.getStatusCode());
      }

    }
}
