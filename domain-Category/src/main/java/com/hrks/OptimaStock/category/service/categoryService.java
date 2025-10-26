package com.hrks.OptimaStock.category.service;

import com.hrks.OptimaStock.category.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class categoryService {

    @Autowired
    private com.hrks.OptimaStock.category.repository.categoryRepository categoryRepository;

    // CREATE
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    // READ - All
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // READ - By ID
    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    // UPDATE
    public Category updateCategory(Long id, Category categoryDetails) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category existingCategory = optionalCategory.get();
            existingCategory.setDescriptionCategory(categoryDetails.getDescriptionCategory());
            return categoryRepository.save(existingCategory);
        } else {
            throw new RuntimeException("Category with ID " + id + " not found");
        }
    }

    // DELETE
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category with ID " + id + " not found");
        }
        categoryRepository.deleteById(id);
    }
}
