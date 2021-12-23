package com.mood.mood.repository;

import com.mood.mood.model.Establishment;
import com.mood.mood.model.EstablishmentImage;
import com.mood.mood.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstablishementImageRepository extends JpaRepository<EstablishmentImage, Integer> {
    Optional<EstablishmentImage> findById(Integer id);
    EstablishmentImage findByDataName(String image_name);
    Optional<Image> findByEstablishment(Establishment establishment);
}
