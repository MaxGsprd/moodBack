package com.mood.mood.service;

import com.mood.mood.controller.ImageController;
import com.mood.mood.controller.LocalisationController;
import com.mood.mood.dto.in.EstablishmentForm;
import com.mood.mood.dto.in.LocalisationForm;
import com.mood.mood.dto.out.CommentDetails;
import com.mood.mood.dto.out.EstablishmentDetails;
import com.mood.mood.dto.out.LocalisationCoordinates;
import com.mood.mood.dto.out.LocalisationDetails;
import com.mood.mood.model.*;
import com.mood.mood.repository.*;
import com.mood.mood.util.LocalisationUtil;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Service
public class EstablishmentService implements IEstablishmentService {

    @Autowired //used to tell spring this bean requires to be injected
    private final EstablishmentRepository establishmentRepository;
    @Autowired
    private final CommentRepository commentRepository;
    @Autowired
    private final CommentService commentService;
    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final NoteRepository noteRepository;
    @Autowired
    private final NoteService noteService;
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private LocalisationRepository localisationRepository;
    @Autowired
    private LocalisationController localisationController;
    @Autowired
    private LocalisationUtil localisationUtil;
    @Autowired
    private ImageController imageController;

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Recherche etabliqhement par localisation + ou - 20 km
     *
     * @param id
     * @return
     */

    public EstablishmentDetails getEstablishmentById(final int id) {
        Establishment establishment = establishmentRepository.findById(id);
        return convertEstablishmentEntityToDto(establishment);
    }

    public List<EstablishmentDetails> getAllEstablishmentsByCategoryId(final int id) {
        return establishmentRepository.findByCategoryId(id)
                .stream()
                .map(this::convertEstablishmentEntityToDto)
                .collect(Collectors.toList());
    }

    public List<EstablishmentDetails> getAllEstablishmentsChecked() {
          return establishmentRepository.getAllEstablishmentChecked()
                .stream()
                .map(this::convertEstablishmentEntityToDto)
                .collect(Collectors.toList());
    }

    public List<EstablishmentDetails> getAllEstablishmentsUnChecked() {
        return establishmentRepository.getAllEstablishmentUnChecked()
                .stream()
                .map(this::convertEstablishmentEntityToDto)
                .collect(Collectors.toList());
    }

    public List<EstablishmentDetails> getAllEstablishments() {
        return establishmentRepository.findAll()
                .stream()
                .map(this::convertEstablishmentEntityToDto)
                .collect(Collectors.toList());
    }


    /**
     * Return all establishments sorted By notes average from best to last
     * */
    public List<EstablishmentDetails> getAllEstablishmentsByNotesAverages() throws Exception {
        List<Establishment> establishments = establishmentRepository.getAllEstablishmentChecked();
        List<EstablishmentDetails> establishmentDetails = establishments.stream().map(this::convertEstablishmentEntityToDto).collect(Collectors.toList());
        List<EstablishmentDetails> sortedEstablishmentDetails = establishmentDetails.stream()
                .sorted((o1,o2) -> (int) (o2.getNote().getNote() - o1.getNote().getNote())).collect(Collectors.toList());
        /** sort stream with comparator on establishmentDetails note */
        return sortedEstablishmentDetails;
    }


    /**
     * Find establishment by name with Like query
     */
    public List<EstablishmentDetails> getEstablishmentByNameLike(String name) throws Exception {
        try {
            return establishmentRepository.findByNameLikeIgnoreCase(name)
                    .stream()
                    .map(this::convertEstablishmentEntityToDto)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    /**
     * @param establishmentForm establishment DTO in
     * @return true if success, false otherwise
     */
    public Establishment createEstablishment(EstablishmentForm establishmentForm) throws Exception {
        try {
            Establishment createdEstablishment = establishmentDtoToEntity(establishmentForm);
            establishmentRepository.save(createdEstablishment);
            return createdEstablishment;
        } catch (Exception e) {
            throw new Exception(e.getMessage(), e.getCause());
        }
    }

    /**
     * @param id establishment id to be deleted
     */
    public void deleteEstablishmentById(int id) throws Exception {
        try {
            Establishment establishment = establishmentRepository.findById(id);
            establishmentRepository.deleteById(establishment.getId());
        } catch (Exception e) {
            throw new Exception("Error : This establishment couldn't be found, " + e.getMessage(), e.getCause());
        }
    }

    /**
     * @param id estblishment id to be updated
     */
    public EstablishmentDetails updateEstablishment(int id, EstablishmentForm establishmentForm) throws Exception {
        try {
            Establishment establishment = establishmentRepository.findById(id);
            establishment.setName(establishmentForm.getName());
            establishment.setDescription(establishmentForm.getDescription());
            establishment.setDescription(establishmentForm.getDescription());
            int categoryId = establishmentForm.getCategory();
            Category category = categoryRepository.findById(categoryId).orElse(null);
            assert category != null;
            establishment.setCategory(category);
            establishmentRepository.save(establishment);
            return convertEstablishmentEntityToDto(establishment);
        } catch (Exception e) {
            throw new Exception("Error : This establishment couldn't be found, " + e.getMessage(), e.getCause());
        }
    }

    /**
     * @param status
     * @return list of EstablishmentDetails
     * @throws Exception
     */
    public List<EstablishmentDetails> getEstablishmentsByStatus(Boolean status) throws Exception {
        return establishmentRepository.findByStatus(status)
                                        .stream()
                                        .map(this::convertEstablishmentEntityToDto)
                                        .collect(Collectors.toList());
    }



    @Override
    public List<Establishment> getEstablishmentWithInDisatance(double lat, double lon, double km) throws Exception {
        try {


            String JM_FORMULE = "(6371 * acos(cos(radians(:latitude)) * cos(radians(l.latitude)) *" +
                    " cos(radians(l.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(l.latitude))))";

            /**
             * SELECT "establishment"."id", description, name, status, category_id, latitude, longitude
             * FROM "public"."establishment"
             * INNER JOIN  "public"."localisation" ON "establishment"."localisation_id" = "localisation"."id";
             *
             */

            double distanceWithInKM = km;
            double longitude = lon;
            double latitude = lat;
            List<Establishment> establishments =  entityManager.createQuery("SELECT e.id, e.description, e.name, e.status, c.description, c.title, l.latitude, l.longitude "+
                            "FROM Establishment e join  e.localisation l join e.category c "+
                            "WHERE (6371 * acos(cos(radians(:latitude)) * cos(radians(l.latitude)) * cos(radians(l.longitude) - radians(:longitude)) + sin(radians(:latitude)) * sin(radians(l.latitude)))) " +
                            "< :distance ORDER BY :JM_FORMULE DESC")
                    .setParameter("JM_FORMULE", JM_FORMULE)
                    .setParameter("longitude", longitude)
                    .setParameter("latitude", latitude)
                    .setParameter("distance", distanceWithInKM)
                    .getResultList();

            return establishments;

        }catch (Exception ex) {
            throw new Exception( ex.getMessage(), ex.getCause());
        }


    }



    @Override
    public List<Establishment> getEstablishmentWithLocalisation() throws Exception {

            //return establishmentRepository.findEstablishmentLocalisation();
            /*List<Establishment> establishment =  entityManager.createQuery("SELECT e.id, e.description, e.name, e.status, l.latitude, l.longitude FROM Establishment e join  e.localisation l WHERE e.id=:id")
                    .setParameter("id",1)
                    .getResultList();*/
        try {


            /**
             * SELECT "establishment"."id" as establishmentID, "establishment"."description" as establishmentDesc, "establishment"."name", "establishment"."status", "categories"."id" as categoryId, "categories"."description" as categoryDesc, "categories"."title", "localisation"."id" as localisationID, "localisation"."latitude", "localisation"."longitude"
             * FROM "public"."establishment"
             * JOIN  "public"."localisation" ON "establishment"."localisation_id" = "localisation"."id"
             * JOIN "public"."categories" ON "establishment"."category_id" = "categories"."id";
             */
            List<Establishment> establishments = entityManager.createQuery("SELECT e.id as establishmentID, e.description as establishmentDescrip, e.name, e.status, c.id as categoryId, c.description as categoryDescrip, c.title, l.id as localisationID, l.latitude, l.longitude " +
                            "FROM Establishment e join  e.localisation l " +
                            "join e.category c")
                    .getResultList();

                return establishments;



        }catch (Exception ex){
            throw new Exception( ex.getMessage(), ex.getCause());
        }
    }


    /**
     * Convert Establishment entity to establishmentDetails (DTO out)
     *
     */
    private EstablishmentDetails convertEstablishmentEntityToDto(Establishment establishment) {
        LocalisationDetails localisationDetails;
        List<Image> img;

        EstablishmentDetails establishmentDetails = new EstablishmentDetails();
        establishmentDetails.setId(establishment.getId());
        establishmentDetails.setName(establishment.getName());
        establishmentDetails.setDescription(establishment.getDescription());
        Localisation localisation = establishment.getLocalisation();
        try {
            localisationDetails = localisationController.getAddressFromLatLon(String.valueOf(localisation.getLatitude()), String.valueOf(localisation.getLongitude()));
        } catch (Exception e) {
            throw new RuntimeException();
        }
        establishmentDetails.setAddress(localisationDetails);

        try {
            img = imageController.getEstablishmentImage(establishment.getName());
        } catch (Exception e) {
            throw new RuntimeException();
        }
        establishmentDetails.setImages(img);
        List<Note> notes = establishment.getNotes();
        establishmentDetails.setNote(noteService.notesAverage(notes));
        List<Comment> comments = establishment.getComments();
        List<CommentDetails> commentDetails = comments.stream().map(comment -> commentService.convertCommentEntityToDto(comment)).collect(Collectors.toList());
        establishmentDetails.setComments(commentDetails);
        establishmentDetails.setCategory(establishment.getCategory());


        return establishmentDetails;
    }

    /**
     * Convert EstablishmentForm (Dto in) to establishment entity
     */
    private Establishment establishmentDtoToEntity(EstablishmentForm establishmentForm) {
        Establishment establishment = null;
        Localisation loc = new Localisation();
        LocalisationCoordinates coordinates = null;

        try {
            establishment = new Establishment();
            establishment.setName(establishmentForm.getName());
            establishment.setDescription(establishmentForm.getDescription());
            LocalisationForm address = null;
            if (establishmentForm.getLocalisationForm() != null) {
                try {
                    address = establishmentForm.getLocalisationForm();
                    coordinates = localisationUtil.getSearchCoordinates(address);
                } catch (Exception e) {
                    throw new RuntimeException();
                }
                long coutId = localisationRepository.count();

                //loc = new Localisation(Integer.parseInt(String.valueOf(coutId+1)),coordinates.getLongitude(), coordinates.getLatitude());

                int newID = Integer.parseInt(String.valueOf(coutId + 1));
                loc.setId(newID);
                loc.setLongitude(coordinates.getLongitude());
                loc.setLatitude(coordinates.getLatitude());

                //loc = new Localisation(coordinates.getLongitude(), coordinates.getLatitude());

                localisationRepository.save(loc);
                establishment.setLocalisation(loc);
            }
            try {
                ResponseEntity<String> image = imageController.uploadEstablishmentFile(establishmentForm.getName(), establishmentForm.getImage());
            } catch (Exception e) {
                throw new RuntimeException();
            }
            int categoryId = establishmentForm.getCategory();
            Category category = categoryRepository.findById(categoryId).orElse(null);
            assert category != null;
            establishment.setCategory(category);
            establishment.setStatus(false);
            return establishment;
        } catch (Exception e) {
            throw new RuntimeException();
        } finally { // une fois l'étabilisement créer ajouter les image associer
            if (establishment != null) {
                try {
                    ResponseEntity<String> image = imageController.uploadEstablishmentFile(establishmentForm.getName(), establishmentForm.getImage());
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
        }
    }
}
