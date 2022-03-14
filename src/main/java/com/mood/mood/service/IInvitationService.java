package com.mood.mood.service;

import com.mood.mood.model.Invitation;

import java.util.List;

public interface IInvitationService {
    List<Invitation> getAllInvitationsByGroupId(int groupId) throws Exception;
    List<Invitation> getAllInvitationsByReceiverId(int receiverId) throws Exception;
    List<Invitation> getAllInvitationsByOrganizerId(int organizerId) throws Exception;
    Invitation createInvitationForGroup(int organizerId, int receiverId, int groupId) throws Exception;
    void deleteInvitationById(int invitationId) throws Exception;
    Invitation updateInvitation(int invitationId, Integer receiverResponse) throws Exception;

}
