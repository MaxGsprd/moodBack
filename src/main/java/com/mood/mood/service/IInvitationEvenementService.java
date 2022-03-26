package com.mood.mood.service;

import com.mood.mood.dto.out.InvitationEvenementDetails;
import com.mood.mood.model.InvitationEvenement;

import java.util.List;

public interface IInvitationEvenementService {
    List<InvitationEvenementDetails> findByInvitationDate(String invitationDate) throws Exception;
    InvitationEvenement createInvitationForEvent(int organizerId, int groupId, int establishmentId) throws Exception;
    InvitationEvenement updateInvitationEvenement(int invitationEvenementId, int receiverResponse) throws Exception;
}
