package com.mood.mood.service;

import com.mood.mood.dto.in.GroupForm;
import com.mood.mood.dto.out.GroupDetails;
import com.mood.mood.dto.out.UserDetails;
import com.mood.mood.model.Category;
import com.mood.mood.model.Group;
import com.mood.mood.model.Role;
import com.mood.mood.model.User;
import com.mood.mood.repository.GroupRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GroupServiceTest {

    @InjectMocks
    private GroupService service;

    @Mock
    private GroupRepository repositoryMock;

    @Mock
    private UserRepository userRepositoryMock;

    private Group group;
    private GroupForm form;
    private User user;

    @BeforeEach
    public void setUp() {
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

        this.form = new GroupForm("title form");
    }

    @Test
    void find() {
        when(this.repositoryMock.findById(0)).thenReturn(Optional.of(this.group));

        GroupDetails result = service.find(0);

        assertEquals(result.getTitle(), "title");
        assertEquals(result.getUsers().get(0).getName(), "Doe");
    }

    @Test
    void create() {
        lenient().when(this.userRepositoryMock.findById(0)).thenReturn(Optional.of(this.user));

        lenient().when(this.repositoryMock.save(this.group)).thenReturn(this.group);

        GroupDetails result = service.create(this.form, 0);

        assertEquals(result.getTitle(), "title form");
        assertEquals(result.getUsers().get(0).getName(), "Doe");
    }

    @Test
    void inviteUser() {
        User newUser = new User();
        newUser.setId(1);
        newUser.setName("Name");
        newUser.addGroup(this.group);
        this.group.addUser(newUser);

        lenient().when(this.repositoryMock.findById(0)).thenReturn(Optional.of(this.group));
        lenient().when(this.userRepositoryMock.findById(1)).thenReturn(Optional.of(newUser));

        GroupDetails result = service.inviteUser(0, 1);

        verify(userRepositoryMock, times(1)).save(newUser);
        verify(repositoryMock, times(1)).save(this.group);

        assertTrue(result.getUsers().contains(newUser));
        assertEquals(result.getTitle(), "title");
    }

    @Test
    void removeUser() {
        lenient().when(this.repositoryMock.findById(0)).thenReturn(Optional.of(this.group));
        lenient().when(this.userRepositoryMock.findById(0)).thenReturn(Optional.of(this.user));

        GroupDetails result = service.removeUser(0, 0);

        verify(repositoryMock, times(1)).save(this.group);

        assertFalse(result.getUsers().contains(this.user));
        assertEquals(result.getTitle(), "title");
    }

    @Test
    void delete() {
        when(this.repositoryMock.findById(0)).thenReturn(Optional.of(this.group));

        assertTrue(service.delete(0));
    }


    @Test
    void rename() {
        when(this.repositoryMock.findById(0)).thenReturn(Optional.of(this.group));

        GroupDetails result = service.rename(0, "new title");

        this.group.setTitle("new title");

        verify(repositoryMock, times(1)).save(this.group);

        assertEquals(result.getTitle(), "new title");
    }
}