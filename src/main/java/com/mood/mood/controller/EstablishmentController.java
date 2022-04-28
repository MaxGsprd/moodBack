package com.mood.mood.controller;

import com.mood.mood.dto.in.EstablishmentForm;
import com.mood.mood.dto.in.EstablishmentLocalisationSearchForm;
import com.mood.mood.dto.out.EstablishmentDetails;
import com.mood.mood.dto.out.LocalisationCoordinates;
import com.mood.mood.model.Establishment;
import com.mood.mood.service.IEstablishmentService;
import com.mood.mood.service.INoteService;
import com.mood.mood.util.LocalisationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
    @Autowired
    private LocalisationUtil localisationUtil;

    @Value("${min.with.in.discance.search}")
    public Double withInDistanceSearch;

    @GetMapping("/establishments")
    public ResponseEntity<?> getAllEstablishments() throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Establishment detail");
        try {
            List<EstablishmentDetails> establishments = establishmentService.getAllEstablishments();
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(establishments);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "**ERROR** -  getting establishment detail :" + e.getMessage(), e.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Impossible de récupérer les informations de tous les établissements!"));
        }
    }

    @GetMapping("/establishmentsByNotesAverage")
    public ResponseEntity<?> getAllEstablishmentsByNotesAverage() throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Establishment by average notes");
        try {
            List<EstablishmentDetails> establishments = establishmentService.getAllEstablishmentsByNotesAverages();
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(establishments);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "**ERROR** -  getting establishment note average :" + e.getMessage(), e.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Impossible de récupérer tous les établissements par moyenne de note!"));
        }
    }

    @GetMapping("/establishments/{name}")
    public ResponseEntity<?> getEstablishmentsByName(@PathVariable String name) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Establishment by name");
        try {
            List<EstablishmentDetails> establishments = establishmentService.getEstablishmentByNameLike("%"+name+"%");
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(establishments);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "**ERROR** -  getting establishment by name :" + e.getMessage(), e.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Impossible de récupérer les informations  de l'établissement %s!", name));
        }
    }

    @GetMapping("/establishments/category/{categoryId}")
    public ResponseEntity<?> getAllEstablishmentsByCategoryId(@PathVariable int categoryId) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Establishment by type of category");
        try {
            List<EstablishmentDetails> establishments = establishmentService.getAllEstablishmentsByCategoryId(categoryId);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(establishments);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "**ERROR** -  getting establishment by category :" + e.getMessage(), e.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Impossible de récupérer les établissements par mood!"));
        }
    }

    @GetMapping("/establishmentsByStatus/{status}")
    public ResponseEntity<?> getAllEstablishmentByStatus(@PathVariable Boolean status) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Establishment by status");
        try {
            List<EstablishmentDetails> establishments = establishmentService.getEstablishmentsByStatus(status);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(establishments);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "**ERROR** -  getting establishment by status :" + e.getMessage(), e.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Impossible de récupérer les établissements par statue!"));
        }
    }

    @GetMapping("/establishment/{id}")
    public ResponseEntity<?> getEstablishmentById(@PathVariable("id") final int id) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Establishment by Id");
        try {
            EstablishmentDetails establishment = establishmentService.getEstablishmentById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(establishment);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,"**ERROR** -  : Establishment with this id couldn't be found, " + e.getMessage(), e.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Impossible de récupérer l\'établissement sous l'identifiant %s!", id));
        }
  }
    @GetMapping("/establishment/withindistancebygeocode")
    public ResponseEntity<?> getEstablishmentWithinDistance(@RequestParam("latitude") double lat, @RequestParam("longitude") double lon, @RequestParam("distance") double km) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Establishment with in selected distance");
        try {
            List<Establishment> establishment = establishmentService.getEstablishmentWithInDisatance(lat, lon, km);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(establishment);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,"**ERROR** -  : Any Establishment with in selected distance, couldn't be found, " + e.getMessage(), e.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Impossible de récupérer les établissements dans ce rayon de distance!"));
        }
    }

    @GetMapping("/establishment/withindistancebyforms")
    public ResponseEntity<?> getEstablishmentWithinDistance(@Valid @RequestBody EstablishmentLocalisationSearchForm establishmentLocalisationSearchForm) throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Establishment with in selected distance");
        try {

            /**
             * Set minimum distacnce search
             */
            double setDefaultDistance = establishmentLocalisationSearchForm.getDistance() == 0  ? withInDistanceSearch : establishmentLocalisationSearchForm.getDistance() + .0;

            LocalisationCoordinates coordinates = localisationUtil.getSearchCoordinates(establishmentLocalisationSearchForm.getLocalisationForm());
            List<Establishment> establishment = establishmentService.getEstablishmentWithInDisatance(coordinates.getLatitude(), coordinates.getLongitude(), setDefaultDistance );
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(establishment);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,"**ERROR** -  : Any Establishment with in selected distance, couldn't be found, " + e.getMessage(), e.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Impossible de récupérer les établissements dans ce rayon de distance!"));
        }
    }

    @GetMapping("/establishment/withLocalisation")
    public ResponseEntity<?> getEstablishmentWithLocalisation() throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get Establishment with localisation");
        try {
            List<Establishment> establishment = (List<Establishment>) establishmentService.getEstablishmentWithLocalisation();
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(establishment);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,"**ERROR** -  : Any Establishment with it's localisation, couldn't be found, " + e.getMessage(), e.getCause());
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(String.format("**ERROR ** - Impossible de récupérer les établissements avec leur coordonait GPS!"));
        }
    }

  @PostMapping("/newEstablishment")
  public ResponseEntity<?> createEstablishment(@Valid @RequestBody EstablishmentForm establishmentForm) throws Exception {
      LOGGER.log(Level.INFO, "**START** - Create new Establishment");
        try {
            Establishment createdEstablishment = establishmentService.createEstablishment(establishmentForm);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEstablishment);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,"**ERROR** - : the establishment couldn't be created " + e.getMessage(), e.getCause());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(String.format("**ERROR ** - Impossible de créer l'établissement !"));
        }
  }

  @DeleteMapping("establishment/{id}")
  @Secured("ROLE_EDITOR")
  public  ResponseEntity<?> deleteEstablishment(@PathVariable int id) throws Exception {
      LOGGER.log(Level.INFO, "**START** - Delete Establishment by id ");
      try {
          establishmentService.deleteEstablishmentById(id);
          HttpHeaders header = new HttpHeaders();
          return ResponseEntity.status(HttpStatus.ACCEPTED)
                  .body(String.format("L'établissement a été supprimer"));
      } catch (Exception ex) {
          LOGGER.log(Level.SEVERE,"**ERROR** - : this establishment deletion request couldn't be executed. " + ex.getMessage(), ex.getCause());
          return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                  .body(String.format("**ERROR ** - Impossible de supprimer ce l'établissement!"));
      }
  }

  @PutMapping("establishment/{id}")
  @Secured("ROLE_EDITOR")
  public ResponseEntity<?> updateEstablishment(@PathVariable("id") int id, @RequestBody EstablishmentForm establishmentForm) throws Exception {
      LOGGER.log(Level.INFO, "**START** - Update Establishment detail");
        try {
            EstablishmentDetails updateEstablishment = establishmentService.updateEstablishment(id, establishmentForm);
            return ResponseEntity.status(HttpStatus.ACCEPTED)
                    .body(updateEstablishment);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE,"**ERROR** -  updating establishment " + e.getMessage(), e.getCause());
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("**ERROR** -- Impossible de modifier ce l'établissement !");

        }
  }


}
