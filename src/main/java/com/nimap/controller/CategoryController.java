package com.nimap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.nimap.entity.Category;
import com.nimap.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    
    @GetMapping
    public Page<Category> getAllCategories(@RequestParam int page, @RequestParam int size) {
        return categoryService.getAllCategories(page, size);
    }

    // Create a new category
    @PostMapping("/create")
    public Category createCategory(@RequestBody Category category) {
    	category.getProducts().forEach(product -> product.setCategory(category));
        return categoryService.createCategory(category);
    }

    // Get category by ID
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    // Update category by ID
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category);
    }

    // Delete category by ID
    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
    }
}
