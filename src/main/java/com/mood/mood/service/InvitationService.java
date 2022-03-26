package com.mood.mood.service;


import com.mood.mood.model.Group;
import com.mood.mood.model.Invitation;
import com.mood.mood.model.User;
import com.mood.mood.repository.GroupRepository;
import com.mood.mood.repository.InvitationRepository;
import com.mood.mood.repository.UserRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@Service
public class InvitationService implements IInvitationService {

    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final InvitationRepository invitationRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final GroupRepository groupRepository;

    public List<Invitation> getAllInvitationsByGroupId(final int groupId) {
        return new ArrayList<>(invitationRepository.findByGroupId(groupId));
    }

    public List<Invitation> getAllInvitationsByReceiverId(final int receiverId) {
        return new ArrayList<>(invitationRepository.findByReceiverId(receiverId));
    }

    public List<Invitation> getAllInvitationsByOrganizerId(final int organizerId) {
        return new ArrayList<>(invitationRepository.findByOrganizerId(organizerId));
    }

    public Invitation createInvitationForGroup(final int organizerId, final int receiverId, final int groupId) throws Exception {
        try {
            Invitation newInvitation = new Invitation();
            Optional<User> organizer = userRepository.findById(organizerId);
            newInvitation.setOrganizer(organizer.get());
            Optional<User> receiver = userRepository.findById(receiverId);
            newInvitation.setReceiver(receiver.get());
            newInvitation.setStatus(0);
            Optional<Group> group = groupRepository.findById(groupId);
            newInvitation.setGroup(group.get());
            invitationRepository.save(newInvitation);
            return newInvitation;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public Invitation updateInvitation(final int invitationId, Integer receiverResponse) throws Exception {
        try {
            Invitation invitation = invitationRepository.findById(invitationId).orElse(null);
            assert invitation != null;
            invitation.setStatus(receiverResponse);
            invitationRepository.save(invitation);
            return invitation;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    /**
     * @param id invitation id to be deleted
     */
    public void deleteInvitationById(int id) throws Exception {
        try {
            Invitation invitation = invitationRepository.findById(id).orElse(null);
            assert invitation != null;
            invitationRepository.deleteById(invitation.getId());
        } catch (Exception e) {
            throw new Exception("Error : This invitation couldn't be found, " + e.getMessage(), e.getCause());
        }
    }

}
