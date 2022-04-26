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

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
            assert organizer.isPresent();
            newInvitationEvenement.setOrganizer(organizer.get());
            newInvitationEvenement.setStatus(0);

            Optional<Group> group = groupRepository.findById(groupId);
            assert group.isPresent();
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
            assert invitationEvenement != null;
            invitationEvenement.setStatus(receiverResponse);
            invitationEvenementRepository.save(invitationEvenement);
            return invitationEvenement;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public List<InvitationEvenementDetails> findByInvitationDate(String invitationDateString) throws Exception {
        try {
            LocalDate invitationDate = LocalDate.parse(invitationDateString);
            return invitationEvenementRepository.findByInvitationDate(invitationDate)
                    .stream()
                    .map(this::convertInvitationEvenementEntityToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public List<InvitationEvenementDetails> findByStatus(int status) throws Exception {
        try {
            return invitationEvenementRepository.findByStatus(status)
                    .stream()
                    .map(this::convertInvitationEvenementEntityToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public List<InvitationEvenementDetails> findByEstablishmentId(int establishmentId) throws Exception {
        try {
            return invitationEvenementRepository.findByEstablishmentId(establishmentId)
                    .stream()
                    .map(this::convertInvitationEvenementEntityToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    public List<InvitationEvenementDetails> findByGroupId(int groupId) throws Exception {
        try {
            return invitationEvenementRepository.findByGroupId(groupId)
                    .stream()
                    .map(this::convertInvitationEvenementEntityToDto)
                    .collect(Collectors.toList());
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