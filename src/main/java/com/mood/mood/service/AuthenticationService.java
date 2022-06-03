package com.mood.mood.service;

import com.mood.mood.dto.in.AuthenticateUser;
import com.mood.mood.dto.in.ForgotPasswordForm;
import com.mood.mood.dto.in.RegisterUser;
import com.mood.mood.dto.out.LocalisationCoordinates;
import com.mood.mood.model.Localisation;
import com.mood.mood.model.User;
import com.mood.mood.repository.CategoryRepository;
import com.mood.mood.repository.LocalisationRepository;
import com.mood.mood.repository.RoleRepository;
import com.mood.mood.repository.UserRepository;
import com.mood.mood.util.JwtUtil;
import com.mood.mood.util.LocalisationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements IAuthenticationService {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private LocalisationRepository localisationRepository;
    @Autowired
    private LocalisationUtil localisationUtil;



    @Override
    public String generateToken(AuthenticateUser authenticateUser) throws Exception {
        try {
            // On authentifie l'utilisateur en passant en paramètre ses credentials
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticateUser.getEmail(), authenticateUser.getPassword())
            );
        } catch (Exception ex) {
            throw new Exception("Invalid email/password" + ex.getMessage(), ex.getCause());
        }

        // Une fois l'utilisateur authentifé, on génère et retourne le token
        return jwtUtil.generateToken(authenticateUser.getEmail());
    }

    //@SneakyThrows
    @Override
    public User createUser(RegisterUser user) throws Exception {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new IllegalArgumentException("Account already exist");
        }

        if(!user.getPassword().equals(user.getConfirmPassword())) {
            throw new IllegalArgumentException("Confirm password doesn't match");
        }

        User createdUser;
       Localisation loc = new Localisation();

        LocalisationCoordinates coordinates = localisationUtil.getSearchCoordinates(user.getLocalisationForm());

        long coutId = localisationRepository.count();

        int newID = Integer.parseInt(String.valueOf(coutId + 1));
        loc.setId(newID);
        loc.setLongitude(coordinates.getLongitude());
        loc.setLatitude(coordinates.getLatitude());

        try {
            localisationRepository.save(loc);
        } catch (Exception ex){
            throw new Exception(ex.getMessage(), ex.getCause());
        }

        createdUser = new User();
        createdUser.setName(user.getName());
        createdUser.setFirstname(user.getFirstname());
        createdUser.setBirthdate(user.getBirthdate());
        createdUser.setEmail(user.getEmail());
        createdUser.setPassword(passwordEncoder.encode(user.getPassword()));
        createdUser.setPhone(user.getPhone());
        createdUser.setLocalisation(loc);
        createdUser.setRole(roleRepository.findByTitle("ROLE_USER"));
        createdUser.setMood(categoryRepository.getById(user.getMood()));


        try {
            userRepository.save(createdUser);
        } catch(Exception e){
            e.printStackTrace();
        }
        return createdUser;
    }

    @Override
    public User forgotPassword(ForgotPasswordForm forgotPasswordForm) throws Exception {
        if (userRepository.findByEmail(forgotPasswordForm.getEmail()) == null) {
            throw new IllegalArgumentException("Account doesn't exist");
        }

        if(!forgotPasswordForm.getPassword().equals(forgotPasswordForm.getConfirmPassword())) {
            throw new IllegalArgumentException("Confirm password doesn't match");
        }

        User updatedUser = userRepository.findByEmail(forgotPasswordForm.getEmail());

        updatedUser.setPassword(forgotPasswordForm.getPassword());

        return userRepository.save(updatedUser);
    }
}
