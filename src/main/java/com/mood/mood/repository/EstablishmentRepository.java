package com.mood.mood.repository;

import com.mood.mood.model.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, Integer> {
    Establishment findById(int id);
    List<Establishment> findAll();
    List<Establishment> findByNameLikeIgnoreCase(String name); //like + ignoreCase query allows finding name like arg with case insensitivity
    List<Establishment> findByCategoryId(int categoryId);
    Establishment findByNameContaining(String name);
    void deleteById(int id);
    List<Establishment> findByStatus(Boolean status);


    /*@Query(value = "SELECT e.id, e.description, e.name, e.status, l.latitude, l.longitude FROM Establishment e join  e.localisation l," ,nativeQuery = true)
    List<Establishment> findEstablishmentLocalisation();*/



}