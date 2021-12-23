package com.mood.mood.controller;

import com.mood.mood.dto.out.EstablishmentDetails;
import com.mood.mood.model.Establishment;
import com.mood.mood.service.EstablishmentService;
import com.mood.mood.service.IEstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/establishments")
public class EstablishmentController {
    @Autowired
    IEstablishmentService establishmentService;

    @GetMapping
    @ResponseBody
    public ResponseEntity<List<Establishment>> getAllEstablishments() throws Exception {
        try {
            List<Establishment> establishments = establishmentService.getAllEstablishments();
            return ResponseEntity.ok(establishments);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<EstablishmentDetails> getEstablishment(@PathVariable int id) throws Exception {
        try {
            EstablishmentDetails establishment = establishmentService.getEstablish(id);
            if (announcement == null)
                return ResponseEntity.notFound().build();
            else
                return ResponseEntity.ok(announcement);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
    }

}
