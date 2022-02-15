package com.mood.mood.controller;

import com.mood.mood.dto.in.GroupForm;
import com.mood.mood.dto.out.GroupDetails;
import com.mood.mood.model.Group;
import com.mood.mood.service.GroupService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    GroupService groupService;

    @GetMapping("/{id}")
    public ResponseEntity<GroupDetails> getGroupDetails(@PathVariable Integer id) throws Exception {
        try {
            GroupDetails group = groupService.find(id);
            return ResponseEntity.ok(group);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
    }

    @PostMapping("{id}/inviteUser/{userId}")
    public ResponseEntity<GroupDetails> inviteUser(@PathVariable Integer id, @PathVariable Integer userId) throws Exception {
        try {
            GroupDetails group = groupService.inviteUser(id, userId);
            return ResponseEntity.ok(group);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
    }

    @PostMapping("{id}/removeUser/{userId}")
    public ResponseEntity<GroupDetails> removeUser(@PathVariable Integer id, @PathVariable Integer userId) throws Exception {
        try {
            GroupDetails group = groupService.removeUser(id, userId);
            return ResponseEntity.ok(group);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
    }

    @PostMapping("/renameGroup/{id}")
    public ResponseEntity<GroupDetails> renameGroup(@PathVariable Integer id, String name) throws Exception {
        try {
            GroupDetails group = groupService.rename(id, name);
            return ResponseEntity.ok(group);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity.HeadersBuilder<?> deleteGroup(@PathVariable Integer id) throws Exception {
        try {
            return groupService.delete(id) ? ResponseEntity.noContent() : ResponseEntity.notFound();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
    }

}
