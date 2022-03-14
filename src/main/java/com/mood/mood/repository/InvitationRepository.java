package com.mood.mood.repository;

import com.mood.mood.model.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Integer> {
    List<Invitation> findByGroupId(int groupId);
    List<Invitation> findByReceiverId(int receiverId);
    List<Invitation> findByOrganizerId(int organizerId);
}