package com.mood.mood.controller;

import com.google.gson.Gson;
import com.mood.mood.dto.in.CommentForm;
import com.mood.mood.service.CommentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentService service;

    private CommentForm form;

    private Gson gson;

    @Before
    public void setUp() throws Exception {
        this.form = new CommentForm(
                "title",
                "content",
                1
        );
        this.gson = new Gson();
    }

    @Test
    public void getAllComments() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/comments")
                .contentType(MediaType.APPLICATION_JSON);

        var result = mockMvc.perform(requestBuilder);
        result.andExpect(status().isOk());
    }


}