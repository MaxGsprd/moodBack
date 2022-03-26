package com.mood.mood.repository;

import com.mood.mood.model.InvitationEvenement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface InvitationEvenementRepository extends JpaRepository<InvitationEvenement, Integer> {
    List<InvitationEvenement> findByInvitationDate(LocalDate invitationDate);
    List<InvitationEvenement> findByStatus(Integer status);
    List<InvitationEvenement> findByEstablishmentId(int establishmentId);
    List<InvitationEvenement> findByGroupId(int groupId);
}
