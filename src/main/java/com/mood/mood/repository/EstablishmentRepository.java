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
    Establishment findByLocalisation(String localisation); // a transformer en localisation

    String JM_FORMULE = "(6371 * acos(cos(radians(:latitude)) * cos(radians(establishment.latitude)) *" +
            " cos(radians(establishment.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(establishment.latitude))))";

    /**
     * SELECT "establishment"."id", description, name, status, category_id, latitude, longitude
     * FROM "public"."establishment"
     * INNER JOIN  "public"."localisation" ON "establishment"."localisation_id" = "localisation"."id";
     *
     */
    @Query(value = "SELECT establishment.id, description, name, status, category_id, latitude, longitude" +
                    "FROM public.establishment" +
                    "INNER JOIN  public.localisation ON establishment.localisation_id = localisation.id"+ 
                    "WHERE " + JM_FORMULE + " < :distance ORDER BY "+ JM_FORMULE + " DESC",nativeQuery = true)
    List<Establishment> findEstablishmentWithInDistance(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("distance") double distanceWithInKM);;


    /*@Query(value = "SELECT e.id, e.description, e.name, e.status, l.latitude, l.longitude FROM Establishment e join  e.localisation l," ,nativeQuery = true)
    List<Establishment> findEstablishmentLocalisation();*/



}