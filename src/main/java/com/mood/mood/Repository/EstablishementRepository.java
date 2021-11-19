package com.mood.mood.Repository;

import com.mood.mood.model.Category;
import com.mood.mood.model.Comment;
import com.mood.mood.model.Establishment;
import com.mood.mood.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
