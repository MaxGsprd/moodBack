package com.mood.mood.Repository;

import com.mood.mood.model.Establishment;
import com.mood.mood.model.EstablishmentImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstablishementImageRepository extends JpaRepository<EstablishmentImage, Integer> {
    Optional<EstablishmentImage> findById(Integer id);
    EstablishmentImage findByDataName(String image_name);
    EstablishmentImage findByEstablishment(Establishment establishment);
}
