package com.mood.mood.service;

import com.mood.mood.repository.CategoryRepository;
import com.mood.mood.repository.RoleRepository;
import com.mood.mood.repository.UserRepository;
import com.mood.mood.dto.in.AuthenticateUser;
import com.mood.mood.dto.in.ForgotPasswordForm;
import com.mood.mood.dto.in.RegisterUser;
import com.mood.mood.model.Localisation;
import com.mood.mood.model.User;
import com.mood.mood.util.JwtUtil;
import com.mood.mood.util.LocalisationUtil;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.persistence.QueryTimeoutException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class AuthenticationService implements IAuthenticationService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    //@Autowired
    //private RestTemplate restTemplate;
    @Autowired
    private LocalisationUtil localisationUtil;

    private static final String ADDRESS_URL = "https://api-adresse.data.gouv.fr/";

    @Override
    public String generateToken(AuthenticateUser authenticateUser) throws Exception {
        try {
            // On authentifie l'utilisateur en passant en paramètre ses credentials
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticateUser.getEmail(), authenticateUser.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("Invalid email/password");
        }

        // Une fois l'utilisateur authentifé, on génère et retourne le token
        return jwtUtil.generateToken(authenticateUser.getEmail());
    }

    @SneakyThrows
    @Override
    public User createUser(RegisterUser user) throws QueryTimeoutException {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Account already exist");
        }

        if(!user.getPassword().equals(user.getConfirmPassword())) {
            throw new IllegalArgumentException("Confirm password doesn't match");
        }

        /*String address = user.getAddressNumber() + " " +user.getAddressName() + " " + user.getPostalCode() + " " + user.getCity();
        URL url =  new URL("https://api-adresse.data.gouv.fr/search/?q="+address+"&type=housenumber&autocomplete=1");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        String response = con.getResponseMessage();*/

        Object address = localisationUtil.getRegisterAddress(user.getAddressNumber(), user.getAddressName(), user.getPostalCode());
        //System.out.println(address.toString());


        User createdUser = new User(
                user.getName(),
                user.getFirstname(),
                user.getBirthdate(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone(),
                new Localisation(),
                roleRepository.findByTitle("ROLE_USER"),
                categoryRepository.getById(user.getMood())
        );

        userRepository.save(createdUser);

        return createdUser;
    }

    @Override
    public User forgotPassword(ForgotPasswordForm forgotPasswordForm) throws Exception {
        if (userRepository.findByEmail(forgotPasswordForm.getEmail()) == null) {
            throw new IllegalArgumentException("Account doesn't exist");
        }

        if(forgotPasswordForm.getPassword() == forgotPasswordForm.getConfirmPassword()) {
            throw new IllegalArgumentException("Confirm password doesn't match");
        }

        User updatedUser = userRepository.findByEmail(forgotPasswordForm.getEmail());

        updatedUser.setPassword(forgotPasswordForm.getPassword());

        return userRepository.save(updatedUser);
    }
}
