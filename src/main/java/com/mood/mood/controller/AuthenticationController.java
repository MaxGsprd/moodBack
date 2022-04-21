package com.mood.mood.controller;

import com.mood.mood.dto.in.AuthenticateUser;
import com.mood.mood.dto.in.ForgotPasswordForm;
import com.mood.mood.dto.in.RegisterUser;
import com.mood.mood.model.Image;
import com.mood.mood.model.User;
import com.mood.mood.service.IAuthenticationService;
import com.mood.mood.util.LocalisationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
/*@CrossOrigin(origins = "*")*/
@RequestMapping("/authentication")
public class AuthenticationController {
    private static Logger LOGGER = Logger.getLogger(AuthenticationController.class.getName());
    @Autowired
    IAuthenticationService authenticationService;

    @Autowired
    private LocalisationUtil localisationUtil;

    @PostMapping("/login")
    public String login(@Valid @RequestBody AuthenticateUser user) throws Exception {
        try {
            return authenticationService.generateToken(user);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return "**ERROR** -- Bad login/password";
        }
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterUser user) throws Exception {//@RequestBody
        try {
            User createdUser = authenticationService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }

    @PatchMapping("/forgotPassword")
    public ResponseEntity<User> forgotPassword(@Valid @RequestBody ForgotPasswordForm form) throws Exception {
        try {
            User user = authenticationService.forgotPassword(form);
            return ResponseEntity.ok(user);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return null;
        }
    }
}
