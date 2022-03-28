package com.mood.mood.controller;

import com.mood.mood.dto.in.EstablishmentForm;
import com.mood.mood.dto.out.EstablishmentDetails;
import com.mood.mood.model.Establishment;
import com.mood.mood.service.IEstablishmentService;
import com.mood.mood.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EstablishmentController {
    @Autowired
    IEstablishmentService establishmentService;
    @Autowired
    INoteService noteService;

    @GetMapping("/establishments")
    public ResponseEntity<List<EstablishmentDetails>> getAllEstablishments() throws Exception {
        try {
            List<EstablishmentDetails> establishments = establishmentService.getAllEstablishments();
            return ResponseEntity.ok(establishments);
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    @GetMapping("/establishmentsByNotesAverage")
    public ResponseEntity<List<EstablishmentDetails>> getAllEstablishmentsByNotesAverage() throws Exception {
        try {
            List<EstablishmentDetails> establishments = establishmentService.getAllEstablishmentsByNotesAverages();
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

    @GetMapping("/establishmentsByStatus/{status}")
    public ResponseEntity<List<EstablishmentDetails>> getAllEstablishmentByStatus(@PathVariable Boolean status) throws Exception {
        try {
            List<EstablishmentDetails> establishments = establishmentService.getEstablishmentsByStatus(status);
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
            throw new Exception("Error : Establishment with this id couldn't be found, " + e.getMessage(), e.getCause());
        }
  }

  @PostMapping("/newEstablishment")
  public ResponseEntity<Establishment> createEstablishment(@Valid @RequestBody EstablishmentForm establishmentForm) throws Exception {
        try {
            Establishment createdEstablishment = establishmentService.createEstablishment(establishmentForm);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEstablishment);
        } catch (Exception e) {
            throw new Exception("Error: the establishment couldn't be created " + e.getMessage(), e.getCause());
        }
  }

  @DeleteMapping("establishment/{id}")
  public  ResponseEntity<Void> deleteEstablishment(@PathVariable int id) throws Exception {
      try {
          establishmentService.deleteEstablishmentById(id);
          HttpHeaders header = new HttpHeaders();
          header.add("Establishment deleted", "The establishment has been successfully deleted");
          return ResponseEntity.status(HttpStatus.OK).build();
      } catch (Exception ex) {
          throw new Exception("Error: this establishment deletion request couldn't be executed. " + ex.getMessage(), ex.getCause());
      }
  }

  @PutMapping("establishment/{id}")
  public ResponseEntity<EstablishmentDetails> updateEstablishment(@PathVariable("id") int id, @RequestBody EstablishmentForm establishmentForm) throws Exception {
        try {
            EstablishmentDetails updateEstablishment = establishmentService.updateEstablishment(id, establishmentForm);
            return ResponseEntity.ok(updateEstablishment);
        } catch (Exception e) {
            throw new Exception("Error updating establishment " + e.getMessage(), e.getCause());
        }
  }


}
