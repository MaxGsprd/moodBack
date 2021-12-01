package com.mood.mood.repository;

import com.mood.mood.model.Group;
import com.mood.mood.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    Group findByTitle(String Title);
    Group findByUpdatedDate(LocalDateTime updateDate);
    List<Group> findByUsers(User user);
}
