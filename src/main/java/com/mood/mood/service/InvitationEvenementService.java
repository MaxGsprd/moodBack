package com.mood.mood.service;

import com.mood.mood.dto.out.InvitationEvenementDetails;
import com.mood.mood.model.*;
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

    public InvitationEvenement createInvitationForEvent(int organizerId, int groupId, int establishmentId) throws Exception {
        try {
            InvitationEvenement newInvitationEvenement = new InvitationEvenement();
            Optional<User> organizer = userRepository.findById(organizerId);
            newInvitationEvenement.setOrganizer(organizer.get());
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

    public InvitationEvenement updateInvitationEvenement(final int invitationEvenementId, final int receiverResponse) throws Exception {
        try {
            InvitationEvenement invitationEvenement = invitationEvenementRepository.findById(invitationEvenementId).orElse(null);
            invitationEvenement.setStatus(receiverResponse);
            invitationEvenementRepository.save(invitationEvenement);
            return invitationEvenement;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    /**
     * @param invitationEvenement  convert InvitationEvenement entity to invitationDetails (DTO out)
     * @return InvitationDetails
     */
    private InvitationEvenementDetails convertInvitationEvenementEntityToDto(InvitationEvenement invitationEvenement) {
        return modelMapper.map(invitationEvenement, InvitationEvenementDetails.class);
    }
}
