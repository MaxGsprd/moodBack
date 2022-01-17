package com.mood.mood.repository;

import com.mood.mood.model.Category;
import com.mood.mood.model.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, Integer> {

    Establishment findById(int id);
    List<Establishment> findByNameLikeIgnoreCase(String name); //like + ignoreCase query allows finding name like arg with case insensitivity
    List<Establishment> findByCategoryId(int categoryId);
    Establishment findByNameContaining(String name);
    Establishment findByLocalisation(String localisation); // a transformer en localisation
    List<Establishment> findByCategory(Category category);
//    Establishment deleteById();
//    List<Establishment> findByStatus(Boolean status);
}