package com.mood.mood.Repository;

import com.mood.mood.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository  extends JpaRepository<Image, Integer> {
    Image findByDataName(String image_name);
}
