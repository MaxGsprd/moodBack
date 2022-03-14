package com.mood.mood.service;

import com.mood.mood.model.InvitationEvenement;

public interface IInvitationEvenementService {
    InvitationEvenement createInvitationForEvent(int organizerId, int groupId, int establishmentId) throws Exception;
}
