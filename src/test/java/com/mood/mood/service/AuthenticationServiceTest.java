package com.mood.mood.service;

import com.mood.mood.dto.in.AuthenticateUser;
import com.mood.mood.dto.in.ForgotPasswordForm;
import com.mood.mood.dto.in.LocalisationForm;
import com.mood.mood.dto.in.RegisterUser;
import com.mood.mood.dto.in.UserForm;
import com.mood.mood.model.Category;
import com.mood.mood.model.Role;
import com.mood.mood.model.User;
import com.mood.mood.repository.CategoryRepository;
import com.mood.mood.repository.RoleRepository;
import com.mood.mood.repository.UserRepository;
import com.mood.mood.util.JwtUtil;
import com.mood.mood.util.LocalisationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @InjectMocks
    private AuthenticationService service;

    @Mock
    private JwtUtil jwtUtil;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private RoleRepository roleRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @Mock
    private RestTemplate restTemplate;
    @Mock
    private LocalisationUtil localisationUtil;

    private String token;
    private User user;
    private AuthenticateUser loginForm;
    private ForgotPasswordForm passwordForm;
    private RegisterUser form;

    private LocalisationForm localisationForm;
    private Role role;
    private Category category;

    @Test
    void generateToken() throws Exception {
        this.loginForm = new AuthenticateUser(
                "john.doe@test.com",
                "test1234"
        );

        this.token = "thisisatesttoken";

        when(jwtUtil.generateToken(this.loginForm.getEmail())).thenReturn(this.token);

        String result = service.generateToken(this.loginForm);

        verify(authenticationManager, times(1)).authenticate(new UsernamePasswordAuthenticationToken(this.loginForm.getEmail(), this.loginForm.getPassword()));

        assertEquals(result, this.token);
    }

    @Test
    void createUser() {

        this.localisationForm = new LocalisationForm();
        this.localisationForm.setAddressNumber("29");
        this.localisationForm.setAddressName("Rue Saint Germain");
        this.localisationForm.setPostalCode("91760");
        this.localisationForm.setCity("Itteville");

        this.form = new RegisterUser();
        this.form.setName("Doe");
        this.form.setFirstname("John");
        this.form.setPassword("test1234");
        this.form.setConfirmPassword("test1234");
        this.form.setBirthdate(LocalDate.of(1990, 4, 1));
        this.form.setLocalisationForm(this.localisationForm);
        this.form.setEmail("john.doe@test.com");
        this.form.setPhone("0123456789");
        this.form.setMood(1);

        this.role = new Role(
                0,
                "ROLE_USER"
        );

        this.category = new Category(
                1,
                "CHILL",
                ""
        );

        // EXCEPTION : ACCOUNT ALREADY EXIST
        when(userRepository.findByEmail(this.form.getEmail())).thenReturn(new User());

        IllegalArgumentException accountThrown = assertThrows(IllegalArgumentException.class, () -> service.createUser(this.form));
        assertEquals("Account already exist", accountThrown.getMessage());

        lenient().when(userRepository.findByEmail(this.form.getEmail())).thenReturn(null);
        // EXCEPTION : WRONG PASSWORD
        RegisterUser userWrongPassword = new RegisterUser();
        userWrongPassword.setPassword("test");
        userWrongPassword.setConfirmPassword("tets");

        IllegalArgumentException passwordThrown = assertThrows(IllegalArgumentException.class, () -> service.createUser(userWrongPassword));
        assertEquals("Confirm password doesn't match", passwordThrown.getMessage());

        // OK CREATEUSER
        lenient().when(roleRepository.findByTitle("ROLE_USER")).thenReturn(this.role);
        lenient().when(categoryRepository.getById(1)).thenReturn(this.category);

        User result = null;
        try {
            result = service.createUser(this.form);
        } catch (Exception ex) {
            throw new RuntimeException();
        }
        assertEquals(result.getName(), this.form.getName());
        assertEquals(result.getFirstname(), this.form.getFirstname());
    }

    @Test
    void forgotPassword() throws Exception {
        this.passwordForm = new ForgotPasswordForm(
                "john.doe@test.com",
                "test",
                "test"
        );

        this.user = new User();
        this.user.setEmail("john.doe@test.com");

        // EXCEPTION : ACCOUNT DOESN'T EXIST
        when(userRepository.findByEmail(this.passwordForm.getEmail())).thenReturn(null);

        IllegalArgumentException accountThrown = assertThrows(IllegalArgumentException.class, () -> service.forgotPassword(this.passwordForm));
        assertEquals("Account doesn't exist", accountThrown.getMessage());

        // EXCEPTION : CONFIRM PASSWORD DOESN'T MATCH
        ForgotPasswordForm userWrongPassword = new ForgotPasswordForm();
        userWrongPassword.setEmail("test@test.com");
        userWrongPassword.setPassword("test");
        userWrongPassword.setConfirmPassword("tets");

        lenient().when(userRepository.findByEmail(userWrongPassword.getEmail())).thenReturn(new User());


        IllegalArgumentException passwordThrown = assertThrows(IllegalArgumentException.class, () -> service.forgotPassword(userWrongPassword));
        assertEquals("Confirm password doesn't match", passwordThrown.getMessage());

        // FORGOTPASSWORD PASS
        this.user.setPassword(this.passwordForm.getPassword());
        this.user.setEmail("john.doe@test.com");
        lenient().when(userRepository.findByEmail(this.passwordForm.getEmail())).thenReturn(this.user);
        lenient().when(userRepository.save(this.user)).thenReturn(this.user);

        User result = service.forgotPassword(this.passwordForm);

        assertEquals(result.getEmail(), this.user.getEmail());
        assertEquals(result.getPassword(), this.user.getPassword());
    }
}