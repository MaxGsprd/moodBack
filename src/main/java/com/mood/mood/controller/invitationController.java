package com.mood.mood.controller;

import com.mood.mood.dto.out.InvitationEvenementDetails;
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
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class invitationController {
    private static Logger LOGGER = Logger.getLogger(invitationController.class.getName());

    @Autowired
    IInvitationService invitationService;
    @Autowired
    IInvitationEvenementService invitationEvenementService;

    @GetMapping("invitations/group/{groupId}")
    public ResponseEntity<List<Invitation>> getAllInvitationDetailsByGroupId(@PathVariable int groupId) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get All Invitation");
        try {
            List<Invitation> invitations = invitationService.getAllInvitationsByGroupId(groupId);
            return ResponseEntity.ok(invitations);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** - : couldn't get Invitation : " + ex.getMessage(), ex.getCause());
            return null;
        }
    }

    @GetMapping("invitationsByReceiver/{receiverId}")
    public ResponseEntity<List<Invitation>> getAllInvitationDetailsByReceiverId(@PathVariable int receiverId) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Invitation send to receiver");
        try {
            List<Invitation> invitations = invitationService.getAllInvitationsByReceiverId(receiverId);
            return ResponseEntity.ok(invitations);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** - : couldn't get Invitation send : " + ex.getMessage(), ex.getCause());
            return null;
        }
    }

    @GetMapping("invitationsByOrganizer/{organizerId}")
    public ResponseEntity<List<Invitation>> getAllInvitationDetailsByOrganizerId(@PathVariable int organizerId) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Invitation send to Organizer");
        try {
            List<Invitation> invitations = invitationService.getAllInvitationsByOrganizerId(organizerId);
            return ResponseEntity.ok(invitations);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** - : couldn't get Invitation organizer : " + ex.getMessage(), ex.getCause());
            return null;
        }
    }


    @GetMapping("invitationEvenementByDate/{date}")
    public ResponseEntity<List<InvitationEvenementDetails>> getAllInvitationEvenementByDate(@PathVariable String date) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Invitation Events");
        try {
            List<InvitationEvenementDetails> invitations = invitationEvenementService.findByInvitationDate(date);
            return ResponseEntity.ok(invitations);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** - : couldn't get Invitation Events : " + ex.getMessage(), ex.getCause());
            return null;
        }
    }

    @GetMapping("invitationEvenementByStatus/{status}")
    public ResponseEntity<List<InvitationEvenementDetails>> getAllInvitationEvenementByStatus(@PathVariable int status) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Invitation Events Status");
        try {
            List<InvitationEvenementDetails> invitations = invitationEvenementService.findByStatus(status);
            return ResponseEntity.ok(invitations);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** - : couldn't get Status Invitation Events : " + ex.getMessage(), ex.getCause());
            return null;
        }
    }

    @GetMapping("invitationEvenementByEstablishment/{establishmentId}")
    public ResponseEntity<List<InvitationEvenementDetails>> getAllInvitationEvenementByEstablishment(@PathVariable int establishmentId) throws Exception {
       LOGGER.log(Level.INFO, "**START** - Get Invitation Event Establishment place");
        try {
            List<InvitationEvenementDetails> invitations = invitationEvenementService.findByEstablishmentId(establishmentId);
            return ResponseEntity.ok(invitations);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** - : couldn't get Establishment Invitation Events : " + ex.getMessage(), ex.getCause());
            return null;
        }
    }

    @GetMapping("invitationEvenementByGroup/{groupId}")
    public ResponseEntity<List<InvitationEvenementDetails>> getAllInvitationEvenementByGroup(@PathVariable int groupId) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Invitation Event Group");
        try {
            List<InvitationEvenementDetails> invitations = invitationEvenementService.findByGroupId(groupId);
            return ResponseEntity.ok(invitations);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** - : couldn't get Group Invitation Events : " + ex.getMessage(), ex.getCause());
            return null;
        }
    }

    @PostMapping("createInvitationForGroup/{organizerId}+{receiverId}+{groupId}")
    public ResponseEntity<Invitation> createInvitation(@Valid @PathVariable int organizerId,
                                                       @PathVariable int receiverId,
                                                       @PathVariable int groupId) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Create Invitation For Group");
        try {
            Invitation invitation = invitationService.createInvitationForGroup(organizerId,receiverId,groupId);
            return ResponseEntity.status(HttpStatus.CREATED).body(invitation);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** - : couldn't create Invitation group : " + ex.getMessage(), ex.getCause());
            return null;
        }
    }

    @PostMapping("createInvitationEvenement/{organizerId}+{groupId}+{establishmentId}")
    public ResponseEntity<InvitationEvenement> createInvitationEvenement(@Valid @PathVariable int organizerId,
                                                                @PathVariable int groupId,
                                                                @PathVariable int establishmentId) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Create Invitation For Events");
        try {
            InvitationEvenement invitation = invitationEvenementService.createInvitationForEvent(organizerId, groupId, establishmentId);
            return ResponseEntity.status(HttpStatus.CREATED).body(invitation);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** - : couldn't create Invitation events : " + ex.getMessage(), ex.getCause());
            return null;
        }
    }

    @DeleteMapping("invitation/{id}")
    public ResponseEntity<Void> deleteInvitation(@PathVariable("id") int id) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Delete Invitation");
        try {
            invitationService.deleteInvitationById(id);
            HttpHeaders header = new HttpHeaders();
            header.add("Invitation deleted", "The invitation has been successfully deleted");
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** - : couldn't delete Invitation : " + ex.getMessage(), ex.getCause());
            return null;
        }
    }

    @PutMapping("invitation/{id}+{receiverResponse}")
    public Invitation updateInvitation(@PathVariable("id") int id, @PathVariable Integer receiverResponse) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Update Invitation status response");
        try {
            return invitationService.updateInvitation(id, receiverResponse);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** - : couldn't Update Invitation status response : " + ex.getMessage(), ex.getCause());
            return null;
        }
    }

    @PutMapping("invitationEvenement/{id}+{receiverResponse}")
    public InvitationEvenement invitationEvenement(@PathVariable("id") int id, @PathVariable int receiverResponse) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Update Invitation events status response");
        try {
            return invitationEvenementService.updateInvitationEvenement(id, receiverResponse);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** - : couldn't Update Invitation events status response : " + ex.getMessage(), ex.getCause());
            return null;
        }
    }

}
