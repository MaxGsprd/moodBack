package com.mood.mood.controller;

import com.mood.mood.config.InsertDataBDD;
import com.mood.mood.dto.in.EstablishmentForm;
import com.mood.mood.dto.out.EstablishmentDetails;
import com.mood.mood.model.Establishment;
import com.mood.mood.model.Image;
import com.mood.mood.service.IEstablishmentService;
import com.mood.mood.service.INoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class EstablishmentController {
    private static Logger LOGGER = Logger.getLogger(EstablishmentController.class.getName());
    @Autowired
    IEstablishmentService establishmentService;
    @Autowired
    INoteService noteService;

    @GetMapping("/establishments")
    public ResponseEntity<List<EstablishmentDetails>> getAllEstablishments() throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Establishment detail");
        try {
            List<EstablishmentDetails> establishments = establishmentService.getAllEstablishments();
            return ResponseEntity.ok(establishments);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "**ERROR** -  getting establishment detail :" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @GetMapping("/establishmentsByNotesAverage")
    public ResponseEntity<List<EstablishmentDetails>> getAllEstablishmentsByNotesAverage() throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Establishment by average notes");
        try {
            List<EstablishmentDetails> establishments = establishmentService.getAllEstablishmentsByNotesAverages();
            return ResponseEntity.ok(establishments);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "**ERROR** -  getting establishment note average :" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @GetMapping("/establishments/{name}")
    public ResponseEntity<List<EstablishmentDetails>> getEstablishmentsByName(@PathVariable String name) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Establishment by name");
        try {
            List<EstablishmentDetails> establishments = establishmentService.getEstablishmentByNameLike("%"+name+"%");
            return ResponseEntity.ok(establishments);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "**ERROR** -  getting establishment by name :" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @GetMapping("/establishments/category/{categoryId}")
    public ResponseEntity<List<EstablishmentDetails>> getAllEstablishmentsByCategoryId(@PathVariable int categoryId) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Establishment by type of category");
        try {
            List<EstablishmentDetails> establishments = establishmentService.getAllEstablishmentsByCategoryId(categoryId);
            return ResponseEntity.ok(establishments);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "**ERROR** -  getting establishment by category :" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @GetMapping("/establishmentsByStatus/{status}")
    public ResponseEntity<List<EstablishmentDetails>> getAllEstablishmentByStatus(@PathVariable Boolean status) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Establishment by status");
        try {
            List<EstablishmentDetails> establishments = establishmentService.getEstablishmentsByStatus(status);
            return ResponseEntity.ok(establishments);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "**ERROR** -  getting establishment by status :" + e.getMessage(), e.getCause());
            return null;
        }
    }

    @GetMapping("/establishment/{id}")
    public ResponseEntity<EstablishmentDetails> getEstablishmentById(@PathVariable("id") final int id) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Establishment by Id");
        try {
            EstablishmentDetails establishment = establishmentService.getEstablishmentById(id);
            return ResponseEntity.ok(establishment);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,"**ERROR** -  : Establishment with this id couldn't be found, " + e.getMessage(), e.getCause());
            return null;
        }
  }
    @GetMapping("/establishment/withindistance/{km}")
    public ResponseEntity<List<Establishment>> getEstablishmentWithinDistance(@PathVariable("km") final int km) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Establishment with in selected distance");
        try {
            List<Establishment> establishment = (List<Establishment>) establishmentService.getEstablishmentWithInDisatance(km);
            return ResponseEntity.ok(establishment);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,"**ERROR** -  : Any Establishment with in selected distance, couldn't be found, " + e.getMessage(), e.getCause());
            return null;
        }
    }

    @GetMapping("/establishment/withLocalisation")
    public ResponseEntity<List<Establishment>> getEstablishmentWithLocalisation() throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Establishment with localisation");
        try {
            List<Establishment> establishment = (List<Establishment>) establishmentService.getEstablishmentWithLocalisation();
            return ResponseEntity.ok(establishment);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,"**ERROR** -  : Any Establishment with it's localisation, couldn't be found, " + e.getMessage(), e.getCause());
            return null;
        }
    }

  @PostMapping("/newEstablishment")
  public ResponseEntity<Establishment> createEstablishment(@Valid @RequestBody EstablishmentForm establishmentForm) throws Exception {
      LOGGER.log(Level.INFO, "**START** - Create new Establishment");
        try {
            Establishment createdEstablishment = establishmentService.createEstablishment(establishmentForm);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEstablishment);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,"**ERROR** - : the establishment couldn't be created " + e.getMessage(), e.getCause());
            return null;
        }
  }

  @DeleteMapping("establishment/{id}")
  public  ResponseEntity<Void> deleteEstablishment(@PathVariable int id) throws Exception {
      LOGGER.log(Level.INFO, "**START** - Delete Establishment by id ");
      try {
          establishmentService.deleteEstablishmentById(id);
          HttpHeaders header = new HttpHeaders();
          header.add("Establishment deleted", "The establishment has been successfully deleted");
          return ResponseEntity.status(HttpStatus.OK).build();
      } catch (Exception ex) {
          LOGGER.log(Level.SEVERE,"**ERROR** - : this establishment deletion request couldn't be executed. " + ex.getMessage(), ex.getCause());
          return null;
      }
  }

  @PutMapping("establishment/{id}")
  public ResponseEntity<EstablishmentDetails> updateEstablishment(@PathVariable("id") int id, @RequestBody EstablishmentForm establishmentForm) throws Exception {
      LOGGER.log(Level.INFO, "**START** - Update Establishment detail");
        try {
            EstablishmentDetails updateEstablishment = establishmentService.updateEstablishment(id, establishmentForm);
            return ResponseEntity.ok(updateEstablishment);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,"**ERROR** -  updating establishment " + e.getMessage(), e.getCause());
            //return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("**ERROR** -- can't update establishment");
            return null;
        }
  }


}
