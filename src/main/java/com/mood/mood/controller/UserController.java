package com.mood.mood.controller;

import com.mood.mood.dto.in.UserForm;
import com.mood.mood.dto.out.UserDetails;
import com.mood.mood.model.User;
import com.mood.mood.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDetails> getUserDetails(@PathVariable Integer id) throws Exception {
        try {
            UserDetails user = userService.find(id);
            return ResponseEntity.ok(user);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
    }

    @PostMapping("/{id}/updateMood/{mood}")
    public ResponseEntity<User> updateMood(@PathVariable Integer id, @PathVariable Integer mood) throws Exception {
        try {
            User user = userService.updateMood(id, mood);
            return ResponseEntity.ok(user);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
    }

    @PostMapping("/{id}/updateRole/{role}")
    @Secured("ROLE_ADMIN")
    public ResponseEntity<User> updateRole(@PathVariable Integer id, @PathVariable Integer role) throws Exception {
        try {
            User user = userService.updateRole(id, role);

            return ResponseEntity.ok(user);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
    }

    @PostMapping("/{id}/update")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody UserForm form) throws Exception {
        try {
            User user = userService.update(id, form);
            return ResponseEntity.ok(user);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Integer id) throws Exception {
        try {
            userService.delete(id);
            HttpHeaders header = new HttpHeaders();
            header.add("Establishment deleted", "The establishment has been successfully deleted");
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
    }
}
