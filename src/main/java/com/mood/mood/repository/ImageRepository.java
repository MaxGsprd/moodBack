package com.mood.mood.repository;

import com.mood.mood.model.EstablishmentImage;
import com.mood.mood.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository  extends JpaRepository<Image, Integer> {
    Image findByDataName(String image_name);
    Optional<Image> findByEstablishment(List<EstablishmentImage> images);
}
