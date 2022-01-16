package com.mood.mood.controller;

import com.mood.mood.dto.out.EstablishmentDetails;
import com.mood.mood.service.IEstablishmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@RequestMapping("/establishments")
public class EstablishmentController {
    @Autowired
    IEstablishmentService establishmentService;

    @GetMapping("/establishments")
    //@ResponseBody
    // If we use @RestController annotation, then @ResponseBody is not needed to use. This is because
    //@RestController = @Controller + @ResponseBody
    public ResponseEntity<List<EstablishmentDetails>> getAllEstablishments() throws Exception {
        try {
            List<EstablishmentDetails> establishments = establishmentService.getAllEstablishments();
            return ResponseEntity.ok(establishments);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    @GetMapping("/establishments/{name}")
    public ResponseEntity<List<EstablishmentDetails>> getEstablishmentsByName(@PathVariable String name) throws Exception {
        try {
            List<EstablishmentDetails> establishments = establishmentService.getEstablishmentByNameLike("%"+name+"%");
            return ResponseEntity.ok(establishments);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    @GetMapping("/establishments/category/{categoryId}")
    public ResponseEntity<List<EstablishmentDetails>> getAllEstablishmentsByCategoryId(@PathVariable int categoryId) throws Exception {
        try {
            List<EstablishmentDetails> establishments = establishmentService.getAllEstablishmentsByCategoryId(categoryId);
            return ResponseEntity.ok(establishments);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    @GetMapping("/establishment/{id}")
    public ResponseEntity<EstablishmentDetails> getEstablishmentById(@PathVariable("id") final int id) throws Exception {
        try {
            EstablishmentDetails establishment = establishmentService.getEstablishmentById(id);
            return ResponseEntity.ok(establishment);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
  }

}
