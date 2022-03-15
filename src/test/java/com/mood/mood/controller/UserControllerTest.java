package com.mood.mood.controller;

import com.google.gson.Gson;
import com.mood.mood.dto.in.UserForm;
import com.mood.mood.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    private UserForm form;

    private Gson gson;

    @BeforeEach
    void setUp() {
        this.form = new UserForm();
        this.gson = new Gson();
    }

    @Test
    void getUserDetails() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/{id}", 0)
                .contentType(MediaType.APPLICATION_JSON);

        var result = mockMvc.perform(requestBuilder);
        result.andExpect(status().isOk());
    }

    @Test
    void updateMood() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/0/updateMood/{mood}", 2)
                .contentType(MediaType.APPLICATION_JSON);

        var result = mockMvc.perform(requestBuilder);
        result.andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username="admin",roles={"ADMIN"})
    void updateRole() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/0/updateRole/{role}", 2)
                .contentType(MediaType.APPLICATION_JSON);

        var result = mockMvc.perform(requestBuilder);
        result.andExpect(status().isOk());
    }

    @Test
    void updateUser() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user/{id}/update", 0)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.gson.toJson(this.form));

        var result = mockMvc.perform(requestBuilder);
        result.andExpect(status().isOk());
    }

    @Test
    void deleteGroup() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/user/{id}", 0)
                .contentType(MediaType.APPLICATION_JSON);

        var result = mockMvc.perform(requestBuilder);
        result.andExpect(status().isOk());
    }
}