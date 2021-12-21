package com.mood.mood.Repository;

import com.mood.mood.model.Localisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocalisationRepository extends JpaRepository<Localisation, Integer> {
    //Localisation getByAddress(String longitude, String latitude);
}
