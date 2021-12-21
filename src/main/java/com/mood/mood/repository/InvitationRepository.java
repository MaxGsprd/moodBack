package com.mood.mood.repository;

import com.mood.mood.model.Group;
import com.mood.mood.model.Invitation;
import com.mood.mood.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Integer> {
    Invitation findByGroup(Group group);
    List<Invitation> findByReceiver(User receiver);
    List<Invitation> findByOrganizer(User organizer);
}