package com.mood.mood.service;

import com.mood.mood.dto.in.AuthenticateUser;
import com.mood.mood.dto.in.ForgotPasswordForm;
import com.mood.mood.dto.in.RegisterUser;
import com.mood.mood.model.User;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.QueryTimeoutException;

public interface IAuthenticationService {
    String generateToken(@RequestBody AuthenticateUser authenticateUser) throws Exception;
    User createUser(@ModelAttribute RegisterUser user) throws QueryTimeoutException;
    User forgotPassword(@RequestBody ForgotPasswordForm forgotPasswordForm) throws Exception;
}
