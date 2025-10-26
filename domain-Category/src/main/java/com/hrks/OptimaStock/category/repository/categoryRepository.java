package com.hrks.OptimaStock.category.repository;

import com.hrks.OptimaStock.category.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface categoryRepository extends JpaRepository<Category, Long> {
}
