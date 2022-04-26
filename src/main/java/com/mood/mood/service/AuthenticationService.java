package com.mood.mood.service;

import com.mood.mood.dto.in.AuthenticateUser;
import com.mood.mood.dto.in.ForgotPasswordForm;
import com.mood.mood.dto.in.RegisterUser;
import com.mood.mood.dto.out.LocalisationCoordinates;
import com.mood.mood.model.GeoCoordinates;
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
import org.springframework.stereotype.Service;

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
            throw new Exception("Invalid email/password");
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

        if(user.getPassword() != user.getConfirmPassword()) {
            throw new IllegalArgumentException("Confirm password doesn't match");
        }

       Localisation loc = null;
        if(user.getLocalisationForm() != null) {

            LocalisationCoordinates coordinates = localisationUtil.getSearchCoordinates(user.getLocalisationForm());

            loc = new Localisation(coordinates.getLongitude(), coordinates.getLatitude());

            localisationRepository.save(loc);
        }

        assert loc != null;
        User createdUser = new User(
                user.getName(),
                user.getFirstname(),
                user.getBirthdate(),
                user.getEmail(),
                user.getPassword(),
                user.getPhone(),
                loc,
                roleRepository.findByTitle("ROLE_USER"),
                categoryRepository.findById(user.getMood())
        );

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

        if(forgotPasswordForm.getPassword() == forgotPasswordForm.getConfirmPassword()) {
            throw new IllegalArgumentException("Confirm password doesn't match");
        }

        User updatedUser = userRepository.findByEmail(forgotPasswordForm.getEmail());

        updatedUser.setPassword(forgotPasswordForm.getPassword());

        return userRepository.save(updatedUser);
    }
}
