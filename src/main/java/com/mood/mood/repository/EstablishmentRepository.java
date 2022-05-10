package com.mood.mood.repository;

import com.mood.mood.model.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, Integer> {

    @Query(
            value = "SELECT * FROM Establishment e WHERE e.status = true",
            nativeQuery = true)
    List<Establishment> getAllEstablishmentChecked();

    @Query(
            value = "SELECT * FROM Establishment e WHERE e.status = false",
            nativeQuery = true)
    List<Establishment> getAllEstablishmentUnChecked();
    Establishment findById(int id);
    List<Establishment> findAll();
    @Query(
            value = "SELECT * FROM Establishment e WHERE e.status = true AND e.name LIKE %?1%",
            nativeQuery = true)
    List<Establishment> findByNameLikeIgnoreCase(String name); //like + ignoreCase query allows finding name like arg with case insensitivity
    @Query(
            value = "SELECT * FROM Establishment e WHERE e.status = true AND e.category_id = ?1",
            nativeQuery = true)
    List<Establishment> findByCategoryId(int categoryId);
    @Query(
            value = "SELECT * FROM Establishment e WHERE e.status = true AND e.name LIKE %?1%",
            nativeQuery = true)
    Establishment findByNameContaining(String name);
    void deleteById(int id);
    List<Establishment> findByStatus(Boolean status);



}