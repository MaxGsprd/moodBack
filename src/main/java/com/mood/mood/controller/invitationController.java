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
    public ResponseEntity<?> getAllInvitationDetailsByGroupId(@PathVariable int groupId) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get All Invitation");
        try {
            List<Invitation> invitations = invitationService.getAllInvitationsByGroupId(groupId);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(invitations);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** - : couldn't get Invitation : " + ex.getMessage(), ex.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Impossible de récupérer l'invitaion!"));
        }
    }

    @GetMapping("invitationsByReceiver/{receiverId}")
    public ResponseEntity<?> getAllInvitationDetailsByReceiverId(@PathVariable int receiverId) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Invitation send to receiver");
        try {
            List<Invitation> invitations = invitationService.getAllInvitationsByReceiverId(receiverId);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(invitations);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** - : couldn't get Invitation send : " + ex.getMessage(), ex.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Impossible de récupérer l'invitaion envoyer à l'utilisateur!"));
        }
    }

    @GetMapping("invitationsByOrganizer/{organizerId}")
    public ResponseEntity<?> getAllInvitationDetailsByOrganizerId(@PathVariable int organizerId) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Invitation send to Organizer");
        try {
            List<Invitation> invitations = invitationService.getAllInvitationsByOrganizerId(organizerId);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(invitations);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** - : couldn't get Invitation organizer : " + ex.getMessage(), ex.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Impossible de récupérer l'invitaion de l'oganisateur!"));
        }
    }


    @GetMapping("invitationEvenementByDate/{date}")
    public ResponseEntity<?> getAllInvitationEvenementByDate(@PathVariable String date) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Invitation Events");
        try {
            List<InvitationEvenementDetails> invitations = invitationEvenementService.findByInvitationDate(date);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(invitations);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** - : couldn't get Invitation Events : " + ex.getMessage(), ex.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Impossible de récupérer l'invitaion d'un événement!"));
        }
    }

    @GetMapping("invitationEvenementByStatus/{status}")
    public ResponseEntity<?> getAllInvitationEvenementByStatus(@PathVariable int status) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Invitation Events Status");
        try {
            List<InvitationEvenementDetails> invitations = invitationEvenementService.findByStatus(status);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(invitations);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** - : couldn't get Status Invitation Events : " + ex.getMessage(), ex.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Impossible de récupérer le statue de l'invitaion d'un événement!"));
        }
    }

    @GetMapping("invitationEvenementByEstablishment/{establishmentId}")
    public ResponseEntity<?> getAllInvitationEvenementByEstablishment(@PathVariable int establishmentId) throws Exception {
       LOGGER.log(Level.INFO, "**START** - Get Invitation Event Establishment place");
        try {
            List<InvitationEvenementDetails> invitations = invitationEvenementService.findByEstablishmentId(establishmentId);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(invitations);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** - : couldn't get Establishment Invitation Events : " + ex.getMessage(), ex.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Impossible de récupérer l'établissement associer à l'invitaion!"));
        }
    }

    @GetMapping("invitationEvenementByGroup/{groupId}")
    public ResponseEntity<?> getAllInvitationEvenementByGroup(@PathVariable int groupId) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Invitation Event Group");
        try {
            List<InvitationEvenementDetails> invitations = invitationEvenementService.findByGroupId(groupId);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(invitations);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** - : couldn't get Group Invitation Events : " + ex.getMessage(), ex.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Impossible de récupérer le groupe associer à l'invitaion!"));
        }
    }

    @PostMapping("createInvitationForGroup/{organizerId}+{receiverId}+{groupId}")
    public ResponseEntity<?> createInvitation(@Valid @PathVariable int organizerId,
                                                       @PathVariable int receiverId,
                                                       @PathVariable int groupId) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Create Invitation For Group");
        try {
            Invitation invitation = invitationService.createInvitationForGroup(organizerId,receiverId,groupId);
            return ResponseEntity.status(HttpStatus.CREATED).body(invitation);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** - : couldn't create Invitation group : " + ex.getMessage(), ex.getCause());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(String.format("**ERROR ** - Impossible de créer une invitaion pour ce group!"));
        }
    }

    @PostMapping("createInvitationEvenement/{organizerId}+{groupId}+{establishmentId}")
    public ResponseEntity<?> createInvitationEvenement(@Valid @PathVariable int organizerId,
                                                                @PathVariable int groupId,
                                                                @PathVariable int establishmentId) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Create Invitation For Events");
        try {
            InvitationEvenement invitation = invitationEvenementService.createInvitationForEvent(organizerId, groupId, establishmentId);
            return ResponseEntity.status(HttpStatus.CREATED).body(invitation);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** - : couldn't create Invitation events : " + ex.getMessage(), ex.getCause());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(String.format("**ERROR ** - Impossible de créer une invitaion pour cette événement!"));
        }
    }

    @DeleteMapping("invitation/{id}")
    public ResponseEntity<?> deleteInvitation(@PathVariable("id") int id) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Delete Invitation");
        try {
            invitationService.deleteInvitationById(id);
          return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(String.format("The invitation has been successfully deleted"));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** - : couldn't delete Invitation : " + ex.getMessage(), ex.getCause());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(String.format("**ERROR ** - Impossible de supprimer ce l'invitation!"));
        }
    }

    @PutMapping("invitation/{id}+{receiverResponse}")
    public ResponseEntity<?>  updateInvitation(@PathVariable("id") int id, @PathVariable Integer receiverResponse) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Update Invitation status response");
        try {
            Invitation updateInvitation = invitationService.updateInvitation(id, receiverResponse);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(updateInvitation);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** - : couldn't Update Invitation status response : " + ex.getMessage(), ex.getCause());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(String.format("**ERROR ** - Impossible de modifier cet invitation!"));
        }
    }

    @PutMapping("invitationEvenement/{id}+{receiverResponse}")
    public ResponseEntity<?> invitationEvenement(@PathVariable("id") int id, @PathVariable int receiverResponse) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Update Invitation events status response");
        try {
            InvitationEvenement updateEventInvitation = invitationEvenementService.updateInvitationEvenement(id, receiverResponse);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(updateEventInvitation);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** - : couldn't Update Invitation events status response : " + ex.getMessage(), ex.getCause());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(String.format("**ERROR ** - Impossible de modifier l'invitation pour cet évémnement!"));
        }
    }

}
