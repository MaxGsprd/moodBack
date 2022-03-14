package com.mood.mood.controller;

import com.mood.mood.model.Invitation;
import com.mood.mood.model.InvitationEvenement;
import com.mood.mood.service.IInvitationEvenementService;
import com.mood.mood.service.IInvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class invitationController {

    @Autowired
    IInvitationService invitationService;
    @Autowired
    IInvitationEvenementService invitationEvenementService;

    @GetMapping("invitations/group/{groupId}")
    public ResponseEntity<List<Invitation>> getAllInvitationDetailsByGroupId(@PathVariable int groupId) throws Exception {
        try {
            List<Invitation> invitations = invitationService.getAllInvitationsByGroupId(groupId);
            return ResponseEntity.ok(invitations);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    @GetMapping("invitationsByReceiver/{receiverId}")
    public ResponseEntity<List<Invitation>> getAllInvitationDetailsByReceiverId(@PathVariable int receiverId) throws Exception {
        try {
            List<Invitation> invitations = invitationService.getAllInvitationsByReceiverId(receiverId);
            return ResponseEntity.ok(invitations);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    @GetMapping("invitationsByOrganizer/{organizerId}")
    public ResponseEntity<List<Invitation>> getAllInvitationDetailsByOrganizerId(@PathVariable int organizerId) throws Exception {
        try {
            List<Invitation> invitations = invitationService.getAllInvitationsByOrganizerId(organizerId);
            return ResponseEntity.ok(invitations);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    @PostMapping("createInvitationForGroup/{organizerId}+{receiverId}+{groupId}")
    public ResponseEntity<Invitation> createInvitation(@Valid @PathVariable int organizerId,
                                                       @PathVariable int receiverId,
                                                       @PathVariable int groupId) throws Exception {
        try {
            Invitation invitation = invitationService.createInvitationForGroup(organizerId,receiverId,groupId);
            return ResponseEntity.status(HttpStatus.CREATED).body(invitation);
        } catch (Exception e) {
            throw new Exception("Error: the invitation couldn't be created " + e.getMessage(), e.getCause());
        }
    }

    @PostMapping("createInvitationEvenementForEvent/{organizerId}+{groupId}+{establishmentId}")
    public ResponseEntity<InvitationEvenement> createInvitationEvenement(@Valid @PathVariable int organizerId,
                                                                @PathVariable int groupId,
                                                                @PathVariable int establishmentId) throws Exception {
        try {
            InvitationEvenement invitation = invitationEvenementService.createInvitationForEvent(organizerId, groupId, establishmentId);
            return ResponseEntity.status(HttpStatus.CREATED).body(invitation);
        } catch (Exception e) {
            throw new Exception("Error: the invitation couldn't be created " + e.getMessage(), e.getCause());
        }
    }

    @DeleteMapping("invitation/{id}")
    public ResponseEntity<Void> deleteInvitation(@PathVariable("id") int id) throws Exception {
        try {
            invitationService.deleteInvitationById(id);
            HttpHeaders header = new HttpHeaders();
            header.add("Invitation deleted", "The invitation has been successfully deleted");
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    @PutMapping("{id}+{receiverResponse}")
    public Invitation updateInvitation(@PathVariable("id") int id, @PathVariable Integer receiverResponse) throws Exception {
        try {
            Invitation invitation = invitationService.updateInvitation(id, receiverResponse);
            return invitation;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

}
