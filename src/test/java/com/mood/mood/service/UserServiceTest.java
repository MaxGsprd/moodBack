package com.mood.mood.service;

import com.mood.mood.dto.in.UserForm;
import com.mood.mood.dto.out.UserDetails;

import com.mood.mood.model.Category;
import com.mood.mood.model.Group;
import com.mood.mood.model.Role;
import com.mood.mood.model.User;
import com.mood.mood.repository.CategoryRepository;
import com.mood.mood.repository.RoleRepository;
import com.mood.mood.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repositoryMock;

    @Mock
    private CategoryRepository categoryRepositoryMock;

    @Mock
    private RoleRepository roleRepositoryMock;

    private User user;
    private Group group;
    private Category category;
    private Role role;
    private UserForm form;

    @BeforeEach
    public void before() {
        this.user = new User();
        this.user.setId(0);
        this.user.setFirstname("John");
        this.user.setName("Doe");
        this.user.setBirthdate(LocalDate.of(1990, 4, 1));
        this.user.setEmail("john.doe@test.com");
        this.user.setPhone("01 23 45 67 89");
        this.user.setRole(new Role(1, "ROLE_USER"));
        this.user.setMood(new Category());

        this.group = new Group();
        this.group.setTitle("title");

        this.user.addGroup(this.group);
        this.group.addUser(this.user);

        this.role = new Role(
                3,
                "ROLE_ADMIN"
        );

        this.category = new Category();
        this.category.setId(2);
        this.category.setTitle("DRINK");

        form = new UserForm(
                "Doe",
                "Jane",
                LocalDate.of(1990, 4, 1),
                "29",
                "Rue Saint Germain",
                "91590",
                "Itteville",
                "jane.doe@test.com",
                "01 23 45 67 89",
                1
        );
    }

    @Test
    void find() {
        when(this.repositoryMock.findById(0)).thenReturn(Optional.of(this.user));

        UserDetails result = service.find(0);

        assertEquals(result.getFirstname(), "John");
        assertEquals(result.getName(), "Doe");
    }

    @Test
    void save() {
        when(this.repositoryMock.save(this.user)).thenReturn(this.user);

        User result = service.save(this.user);

        assertEquals(result.getFirstname(), "John");
        assertEquals(result.getName(), "Doe");
    }

    @Test
    void update() throws Exception {
        when(this.repositoryMock.findById(0)).thenReturn(Optional.of(this.user));

        this.user.setFirstname(form.getFirstname());
        this.user.setName(form.getName());
        this.user.setEmail(form.getEmail());
        this.user.setBirthdate(form.getBirthdate());

        when(this.repositoryMock.save(this.user)).thenReturn(this.user);

        User result = service.update(0, form);

        assertEquals(result.getFirstname(), "Jane");
        assertEquals(result.getEmail(), "jane.doe@test.com");
    }

    @Test
    void updateMood() {
        when(this.repositoryMock.findById(0)).thenReturn(Optional.of(this.user));

        this.user.setMood(this.category);

        lenient().when(this.categoryRepositoryMock.findById(2)).thenReturn(Optional.of(this.category));
        when(this.repositoryMock.save(this.user)).thenReturn(this.user);

        User result = service.updateMood(0, 2);

        assertEquals(result.getFirstname(), "John");
        assertEquals(result.getName(), "Doe");
        assertEquals(result.getMood().getTitle(), "DRINK");
    }

    @Test
    void updateRole() {
        when(this.repositoryMock.findById(0)).thenReturn(Optional.of(this.user));

        this.user.setRole(this.role);

        lenient().when(this.roleRepositoryMock.findById(3)).thenReturn(Optional.of(this.role));
        when(this.repositoryMock.save(this.user)).thenReturn(this.user);

        User result = service.updateRole(0, 3);

        assertEquals(result.getFirstname(), "John");
        assertEquals(result.getName(), "Doe");
        assertEquals(result.getRole().getTitle(), "ROLE_ADMIN");
    }

    @Test
    void delete() {
        when(this.repositoryMock.findById(0)).thenReturn(Optional.of(this.user));

        assertTrue(service.delete(0));
    }
}