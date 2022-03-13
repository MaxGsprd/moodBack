package com.mood.mood.service;

import com.mood.mood.dto.out.InvitationDetails;
import com.mood.mood.model.Invitation;

import java.util.List;

public interface IInvitationService {
    List<InvitationDetails> getAllInvitationsByGroupId(int groupId) throws Exception;
    List<InvitationDetails> getAllInvitationsByReceiverId(int receiverId) throws Exception;
    List<InvitationDetails> getAllInvitationsByOrganizerId(int organizerId) throws Exception;
    Invitation createInvitationForGroup(int organizerId, int receiverId, int groupId) throws Exception;

}
