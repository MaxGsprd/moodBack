package com.mood.mood.controller;

import com.google.gson.Gson;
import com.mood.mood.dto.in.EstablishmentForm;
import com.mood.mood.service.EstablishmentService;
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
public class EstablishmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EstablishmentService service;

    private EstablishmentForm form;

    private
    Gson gson;

    @Before
    public void setUp() throws Exception {
        this.form = new EstablishmentForm(
                "name",
                "description",
                1
        );
        this.gson = new Gson();
    }

    @Test
    public void getAllEstablishments() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/establishments/")
                .contentType(MediaType.APPLICATION_JSON);

        var result = mockMvc.perform(requestBuilder);
        result.andExpect(status().isOk());

        // EXCEPTION
        /*RequestBuilder requestBuilderException = MockMvcRequestBuilders.get("/establishments/not_found")
                .contentType(MediaType.APPLICATION_JSON);

        var resultException = mockMvc.perform(requestBuilder);
        result.andExpect(status().isNotFound());*/
    }

    @Test
    public void getEstablishmentsByName() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/establishments/{name}", "test")
                .contentType(MediaType.APPLICATION_JSON)
                .accept("application/json");

        var result = mockMvc.perform(requestBuilder);
        // mockMvc.perform(requestBuilder).andExpect(status().isOk());

        result.andExpect(status().isOk());
        //System.out.println(result.getResponse().getContentAsString());
        //result.andExpect(jsonPath("$[0].name").value("test"));
    }

    @Test
    public void getAllEstablishmentsByCategoryId() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/establishments/category/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept("application/json");

        var result = mockMvc.perform(requestBuilder);

        result.andExpect(status().isOk());
    }

    @Test
    public void getAllEstablishmentByStatus() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/establishmentsByStatus/{status}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept("application/json");

        var result = mockMvc.perform(requestBuilder);

        result.andExpect(status().isOk());
    }

    @Test
    public void getAllEstablishmentsById() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/establishment/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept("application/json");

        var result = mockMvc.perform(requestBuilder);

        result.andExpect(status().isOk());
    }

    @Test
    public void createEstablishment() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/newEstablishment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.gson.toJson(this.form));

        var result = mockMvc.perform(requestBuilder);

        result.andExpect(status().isCreated());
    }

    @Test
    public void updateEstablishment() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/establishment/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.gson.toJson(this.form));

        var result = mockMvc.perform(requestBuilder);

        result.andExpect(status().isOk());
    }

    @Test
    public void deleteEstablishment() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/establishment/1")
                .contentType(MediaType.APPLICATION_JSON);

        var result = mockMvc.perform(requestBuilder);

        result.andExpect(status().isOk());
    }

}