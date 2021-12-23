package com.mood.mood.repository;

import com.mood.mood.model.Category;
import com.mood.mood.model.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstablishementRepository extends JpaRepository<Establishment, Integer> {
    //@Query("SELECT name FROM Establishment WHERE Establishment.name LIKE %:name%")
    //Establishment findbyName(@Param("name") String name);

    Establishment findByNameContaining(String name);
    List<Establishment> findByStatus(Boolean status);
    Establishment findByLocalisation(String address); // a transformer en localisation
    List<Establishment> findByCategory(Category category);
}
