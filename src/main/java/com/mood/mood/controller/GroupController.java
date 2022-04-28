package com.mood.mood.controller;

import com.mood.mood.dto.in.GroupForm;
import com.mood.mood.dto.out.GroupDetails;
import com.mood.mood.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/group")
public class GroupController {
    private static Logger LOGGER = Logger.getLogger(GroupController.class.getName());
    @Autowired
    GroupService groupService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getGroupDetails(@PathVariable Integer id) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Getting Group details by id");
        try {
            GroupDetails group = groupService.find(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(group);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** -  Getting Group details " + ex.getMessage(), ex.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Impossible de récupérer le information du group!"));
        }
    }

    @PostMapping("{id}/inviteUser/{userId}")
    public ResponseEntity<?> inviteUser(@PathVariable Integer id, @PathVariable Integer userId) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Adding a user to a group");
        try {
            GroupDetails group = groupService.inviteUser(id, userId);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(group);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** -  Adding user to a Group " + ex.getMessage(), ex.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Impossible d'invité l'utilisateur au group!"));
        }
    }

    @DeleteMapping("{id}/removeUser/{userId}")
    public ResponseEntity<?> removeUser(@PathVariable Integer id, @PathVariable Integer userId) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Remove a user to a group");
        try {
            GroupDetails group = groupService.removeUser(id, userId);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(group);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** -  Removing user to a Group " + ex.getMessage(), ex.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Impossible de supprimer l'utilisateur au group!" ));
        }
    }

    @PostMapping("/createGroup/{user_id}")
    public ResponseEntity<?> createGroup(@PathVariable Integer user_id,@Valid @RequestBody GroupForm form) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Create a group");
        try {
            GroupDetails group = groupService.create(form, user_id);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(group);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** -  Creating Group " + ex.getMessage(), ex.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Impossible de créer le group %s!", form.getTitle()));
        }
    }

    @PutMapping("/renameGroup/{id}")
    public ResponseEntity<?> renameGroup(@PathVariable Integer id, String name) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Update group name");
        try {
            GroupDetails group = groupService.rename(id, name);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(group);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** -  Updating Group name " + ex.getMessage(), ex.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Impossible de modifier le group %s!", name));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity.HeadersBuilder<?> deleteGroup(@PathVariable Integer id) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Delete group");
        try {
            return groupService.delete(id) ? ResponseEntity.noContent() : ResponseEntity.notFound();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** -  Deleting Group " + ex.getMessage(), ex.getCause());
            return (ResponseEntity.HeadersBuilder<?>) ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Impossible de supprimer ce group!"));
        }
    }

}
