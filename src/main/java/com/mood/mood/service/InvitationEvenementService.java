package com.mood.mood.service;

import com.mood.mood.dto.out.InvitationDetails;
import com.mood.mood.model.Establishment;
import com.mood.mood.model.Group;
import com.mood.mood.model.InvitationEvenement;
import com.mood.mood.model.User;
import com.mood.mood.repository.EstablishmentRepository;
import com.mood.mood.repository.GroupRepository;
import com.mood.mood.repository.InvitationEvenementRepository;
import com.mood.mood.repository.UserRepository;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@Service
public class InvitationEvenementService implements IInvitationEvenementService {

    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final InvitationEvenementRepository invitationEvenementRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final GroupRepository groupRepository;
    @Autowired
    private final EstablishmentRepository establishmentRepository;

    public InvitationEvenement createInvitationForEvent(int organizerId, int receiverId, int groupId, int establishmentId) throws Exception {
        try {
            InvitationEvenement newInvitationEvenement = new InvitationEvenement();
            Optional<User> organizer = userRepository.findById(organizerId);
            newInvitationEvenement.setOrganizer(organizer.get());
            Optional<User> receiver = userRepository.findById(receiverId);
            newInvitationEvenement.setReceiver(receiver.get());
            newInvitationEvenement.setStatus(0);
            Optional<Group> group = groupRepository.findById(groupId);
            newInvitationEvenement.setGroup(group.get());
            Establishment establishment = establishmentRepository.findById(establishmentId);
            newInvitationEvenement.setEstablishment(establishment);
            invitationEvenementRepository.save(newInvitationEvenement);
            return newInvitationEvenement;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    /**
     * @param invitationEvenement  convert InvitationEvenement entity to invitationDetails (DTO out)
     * @return InvitationDetails
     */
    private InvitationDetails convertInvitationEvenementEntityToDto(InvitationEvenement invitationEvenement) {
        return modelMapper.map(invitationEvenement, InvitationDetails.class);
    }
}
