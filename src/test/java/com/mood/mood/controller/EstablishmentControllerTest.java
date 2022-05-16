package com.mood.mood.controller;

import com.google.gson.Gson;
import com.mood.mood.dto.in.EstablishmentForm;
import com.mood.mood.dto.in.LocalisationForm;
import com.mood.mood.model.Category;
import com.mood.mood.model.Role;
import com.mood.mood.model.User;
import com.mood.mood.service.EstablishmentService;
import org.junit.Before;
import org.junit.Test;
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

import java.time.LocalDate;

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
    private LocalisationForm localisationForm;
    private User user;
    


    private
    Gson gson;

    @Before
    public void setUp() throws Exception {
        this.localisationForm = new LocalisationForm();
        this.localisationForm.setAddressNumber("29");
        this.localisationForm.setAddressName("Rue Saint Germain");
        this.localisationForm.setPostalCode("91760");
        this.localisationForm.setCity("Itteville");

        this.form = new EstablishmentForm();
        this.form.setName("Name form");
        this.form.setDescription("description form");
        this.form.setLocalisationForm(this.localisationForm);
        this.form.setCategory(0);
        this.gson = new Gson();
        
        this.user = new User();
        this.user.setId(3);
        this.user.setName("Sangoku");
        this.user.setFirstname("Vegeta");
        this.user.setBirthdate(LocalDate.parse("1990-08-10"));
        this.user.setEmail("dragonb@ll.lz");
        this.user.setPassword( "chichi");
        this.user.setPhone("0629834946");
        this.user.setMood(new Category(1,"BEER","With Friends"));
        this.user.setRole(new Role(2,"ROLE_EDITOR"));
    }

    @Test
    public void getAllEstablishments() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/establishments/")
                .contentType(MediaType.APPLICATION_JSON);

        var result = mockMvc.perform(requestBuilder);
        result.andExpect(status().isAccepted());

    }

    @Test
    public void getEstablishmentsByName() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/establishments/{name}", "test")
                .contentType(MediaType.APPLICATION_JSON)
                .accept("application/json");

        var result = mockMvc.perform(requestBuilder);
        // mockMvc.perform(requestBuilder).andExpect(status().isAccepted());

        result.andExpect(status().isAccepted());
        //System.out.println(result.getResponse().getContentAsString());
        //result.andExpect(jsonPath("$[0].name").value("test"));
    }

    @Test
    public void getAllEstablishmentsByCategoryId() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/establishments/category/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept("application/json");

        var result = mockMvc.perform(requestBuilder);

        result.andExpect(status().isAccepted());
    }

    @Test
    public void getAllEstablishmentByStatus() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/establishmentsByStatus/{status}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept("application/json");

        var result = mockMvc.perform(requestBuilder);

        result.andExpect(status().isAccepted());
    }

    @Test
    public void getAllEstablishmentsById() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/establishment/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept("application/json");

        var result = mockMvc.perform(requestBuilder);

        result.andExpect(status().isAccepted());
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
        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/editor/establishment/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.gson.toJson(this.form));

        var result = mockMvc.perform(requestBuilder);

        result.andExpect(status().isAccepted());
    }

    @Test
    @WithMockUser(roles = "ROLE_EDITOR")
    public void deleteEstablishment() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/editor/establishment/1")
                .contentType(MediaType.APPLICATION_JSON);

        var result = mockMvc.perform(requestBuilder);

        result.andExpect(status().isAccepted());
    }

}