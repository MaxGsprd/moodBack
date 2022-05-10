package com.mood.mood.controller;

import com.mood.mood.dto.in.AuthenticateUser;
import com.mood.mood.dto.in.ForgotPasswordForm;
import com.mood.mood.dto.in.RegisterUser;
import com.mood.mood.model.User;
import com.mood.mood.service.IAuthenticationService;
import com.mood.mood.util.LocalisationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {
    private static Logger LOGGER = Logger.getLogger(AuthenticationController.class.getName());
    @Autowired
    IAuthenticationService authenticationService;

    @Autowired
    private LocalisationUtil localisationUtil;

    //@PostMapping(value = "/login", consumes = "text/plain;charset=UTF-8")
    @PostMapping("/login")
    public ResponseEntity<?>  login(@Valid @RequestBody AuthenticateUser user) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Post login connexion token");
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(String.format(authenticationService.generateToken(user)));

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(String.format("**ERROR** -- Bad login/password"));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUser user) throws Exception {//@RequestBody
        LOGGER.log(Level.INFO, "**START** - Post  register form to create new user");
        try {
            /*if(user.getPassword() != user.getConfirmPassword()) {
                throw new IllegalArgumentException("Confirm password doesn't match");
            }*/
            User createdUser = authenticationService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(String.format("**ERROR ** -Impossible d'enregistrer l'utilisateur! : %s", ex.getMessage()));
        }
    }

    @PatchMapping("/forgotPassword")
    public ResponseEntity<?> forgotPassword(@Valid @RequestBody ForgotPasswordForm form) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Patch forget password");
        try {
            User user = authenticationService.forgotPassword(form);
            return ResponseEntity.ok(user);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(String.format("**ERROR ** -Impossible de réinitialiser le mot de passe! : %s" , ex.getMessage()));
        }
    }
}
