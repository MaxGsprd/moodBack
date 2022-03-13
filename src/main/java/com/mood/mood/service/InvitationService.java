package com.mood.mood.service;


import com.mood.mood.dto.out.InvitationDetails;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public List<InvitationDetails> getAllInvitationsByGroupId(final int groupId) {
        return invitationRepository.findByGroupId(groupId)
                .stream()
                .map(this::convertInvitationEntityToDto)
                .collect(Collectors.toList());
    }

    public List<InvitationDetails> getAllInvitationsByReceiverId(final int receiverId) {
        return invitationRepository.findByReceiverId(receiverId)
                .stream()
                .map(this::convertInvitationEntityToDto)
                .collect(Collectors.toList());
    }

    public List<InvitationDetails> getAllInvitationsByOrganizerId(final int organizerId) {
        return invitationRepository.findByOrganizerId(organizerId)
                .stream()
                .map(this::convertInvitationEntityToDto)
                .collect(Collectors.toList());
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

    /**
     * @param invitation  convert Invitation entity to invitationDetails (DTO out)
     * @return InvitationDetails
     */
    private InvitationDetails convertInvitationEntityToDto(Invitation invitation) {
        return modelMapper.map(invitation, InvitationDetails.class);
    }
}
