package com.mood.mood.controller;

import com.mood.mood.config.InsertDataBDD;
import com.mood.mood.dto.in.GroupForm;
import com.mood.mood.dto.out.GroupDetails;
import com.mood.mood.model.Group;
import com.mood.mood.service.GroupService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/group")
public class GroupController {
    private static Logger LOGGER = Logger.getLogger(GroupController.class.getName());
    @Autowired
    GroupService groupService;

    @GetMapping("/{id}")
    public ResponseEntity<GroupDetails> getGroupDetails(@PathVariable Integer id) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Getting Group details by id");
        try {
            GroupDetails group = groupService.find(id);
            return ResponseEntity.ok(group);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** -  Getting Group details " + ex.getMessage(), ex.getCause());
            return null;
        }
    }

    @PostMapping("{id}/inviteUser/{userId}")
    public ResponseEntity<GroupDetails> inviteUser(@PathVariable Integer id, @PathVariable Integer userId) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Adding a user to a group");
        try {
            GroupDetails group = groupService.inviteUser(id, userId);
            return ResponseEntity.ok(group);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** -  Adding user to a Group " + ex.getMessage(), ex.getCause());
            return null;
        }
    }

    @DeleteMapping("{id}/removeUser/{userId}")
    public ResponseEntity<GroupDetails> removeUser(@PathVariable Integer id, @PathVariable Integer userId) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Remove a user to a group");
        try {
            GroupDetails group = groupService.removeUser(id, userId);
            return ResponseEntity.ok(group);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** -  Removing user to a Group " + ex.getMessage(), ex.getCause());
            return null;
        }
    }

    @PutMapping("/createGroup/{user_id}")
    public ResponseEntity<Group> renameGroup(@PathVariable Integer user_id, GroupForm form) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Create and Update group");
        try {
            Group group = groupService.create(user_id, form);
            return ResponseEntity.ok(group);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** -  Updating Group " + ex.getMessage(), ex.getCause());
            return null;
        }
    }

    @PutMapping("/renameGroup/{id}")
    public ResponseEntity<GroupDetails> renameGroup(@PathVariable Integer id, String name) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Update group name");
        try {
            GroupDetails group = groupService.rename(id, name);
            return ResponseEntity.ok(group);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** -  Updating Group name " + ex.getMessage(), ex.getCause());
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity.HeadersBuilder<?> deleteGroup(@PathVariable Integer id) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Delete group");
        try {
            return groupService.delete(id) ? ResponseEntity.noContent() : ResponseEntity.notFound();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE,"**ERROR** -  Deleting Group " + ex.getMessage(), ex.getCause());
            return null;
        }
    }

}
