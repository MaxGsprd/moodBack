package com.mood.mood.repository;

import com.mood.mood.model.Category;
import com.mood.mood.model.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findById(int id);
}
