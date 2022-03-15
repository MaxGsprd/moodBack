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

    @Test
    public void getCommentByEstablishment() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/commentsByEstablishment/{establishmentId}", 1 )
                .contentType(MediaType.APPLICATION_JSON)
                .accept("application/json");
        var result = mockMvc.perform(requestBuilder);

        result.andExpect(status().isOk());
    }

    @Test
    public void getCommentByStatus() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/commentsByStatus/{status}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept("application/json");

        var result = mockMvc.perform(requestBuilder);
        result.andExpect(status().isOk());
    }

    @Test
    public void getCommentsByUserId() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/commentsByUser/{userId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept("application/json");

        var result = mockMvc.perform(requestBuilder);
        result.andExpect(status().isOk());
    }

    @Test
    public void getCommentsByGroupType() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/commentsByGroupType/{groupType}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept("application/json");

        var result = mockMvc.perform(requestBuilder);
        result.andExpect(status().isOk());
    }

    @Test
    public void createComment() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/newComment/establishment/1/user/11")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.gson.toJson(this.form));

        var result = mockMvc.perform(requestBuilder);
        result.andExpect(status().isCreated());
    }

    @Test
    public void deleteComment() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/comment/{id}",1)
                .contentType(MediaType.APPLICATION_JSON);
        var result = mockMvc.perform(requestBuilder);
        result.andExpect(status().isOk());
    }

    @Test
    public void updateComment() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/comment/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.gson.toJson(this.form));
        var result = mockMvc.perform(requestBuilder);
        result.andExpect(status().isOk());

    }
}