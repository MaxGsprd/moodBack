package com.mood.mood.repository;

import com.mood.mood.model.Establishment;
import com.mood.mood.model.Group;
import com.mood.mood.model.InvitationEvenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface InvitationEvenementRepository extends JpaRepository<InvitationEvenement, Integer> {
    InvitationEvenement findByInvitationDate(LocalDateTime dateTime);
    InvitationEvenement findByStatus(Integer status);
    InvitationEvenement findByEstablishmentId(int establishmentId);
    InvitationEvenement findByGroupId(int groupId);
}
