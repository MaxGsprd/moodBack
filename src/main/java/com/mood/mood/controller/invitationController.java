package com.mood.mood.controller;

import com.mood.mood.service.IInvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class invitationController {

    @Autowired
    IInvitationService invitationService;

}
