package com.mood.mood.repository;

import com.mood.mood.model.Image;
import com.mood.mood.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserImageRepository extends JpaRepository<Image, Integer> {
    Optional<Image> findById(Integer id);
    Image findByDataName(String image_name);
    Image findByUser(User user);
}
