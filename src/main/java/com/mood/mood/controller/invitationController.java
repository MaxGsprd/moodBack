package com.mood.mood.controller;

import com.mood.mood.dto.out.InvitationDetails;
import com.mood.mood.model.Invitation;
import com.mood.mood.service.IInvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class invitationController {

    @Autowired
    IInvitationService invitationService;

    @GetMapping("invitations/group/{groupId}")
    public ResponseEntity<List<InvitationDetails>> getAllInvitationDetailsByGroupId(@PathVariable int groupId) throws Exception {
        try {
            List<InvitationDetails> invitationDetails = invitationService.getAllInvitationsByGroupId(groupId);
            return ResponseEntity.ok(invitationDetails);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    @GetMapping("invitationsByReceiver/{receiverId}")
    public ResponseEntity<List<InvitationDetails>> getAllInvitationDetailsByReceiverId(@PathVariable int receiverId) throws Exception {
        try {
            List<InvitationDetails> invitationDetails = invitationService.getAllInvitationsByReceiverId(receiverId);
            return ResponseEntity.ok(invitationDetails);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    @GetMapping("invitationsByOrganizer/{organizerId}")
    public ResponseEntity<List<InvitationDetails>> getAllInvitationDetailsByOrganizerId(@PathVariable int organizerId) throws Exception {
        try {
            List<InvitationDetails> invitationDetails = invitationService.getAllInvitationsByOrganizerId(organizerId);
            return ResponseEntity.ok(invitationDetails);
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

}
