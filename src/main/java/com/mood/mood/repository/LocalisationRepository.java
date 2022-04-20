package com.mood.mood.repository;

import com.mood.mood.model.Localisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.*;

@Repository
public interface LocalisationRepository extends JpaRepository<Localisation, Serializable>, JpaSpecificationExecutor<Localisation> {
    Optional<Localisation> findByLatitudeAndLongitude(Double latitude, Double longitude);

}
