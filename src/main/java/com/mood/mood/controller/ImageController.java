package com.mood.mood.controller;

import com.mood.mood.config.InsertDataBDD;
import com.mood.mood.model.Image;
import com.mood.mood.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Cette classe contient les réponses HTTP liées aux images
 */
@RestController
@RequestMapping("/image")
public class ImageController {

    private static Logger LOGGER = Logger.getLogger(ImageController.class.getName());
    @Autowired
    private ImageService imageService;


    /**
     * Loop on IMAGE entity to sent an array of all information
     * @param imageEntity
     * @return [image]
     */
    private Image mapToImage(Image imageEntity) {
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(imageEntity.getDataName())
                .toUriString();
        Image fileResponse = new Image();
        fileResponse.setId(imageEntity.getId());
        fileResponse.setDataName(imageEntity.getDataName());
        fileResponse.setMimeType(imageEntity.getMimeType());
        fileResponse.setSizeImage(imageEntity.getSizeImage());

        return fileResponse;
    }

    @GetMapping("/")
    public List<Image> get(Model model)
            throws Exception {
        LOGGER.log(Level.INFO, "**START** -- list all images");
        try {
            List<Image> images = imageService.getFiles()
                    .stream()
                    .map(this::mapToImage)
                    .collect(Collectors.toList());
            return images;
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "**ERROR**  --  image! : " +ex.getMessage(), ex);
            return (List<Image>) ResponseEntity.status(HttpStatus.BAD_REQUEST).body("**ERROR** -- Image can't get list of all images");
        }
    }

    /**
     * LOOP on request display image from list
     * @param id
     * @return
     * @throws Exception
     */
    @GetMapping("/show/{id}")
    public ResponseEntity<?> displayImage(@PathVariable int id)
            throws Exception {
        LOGGER.log(Level.INFO, "start show image by id");
        Image image = null;
        try {
            image = imageService.getImageById(id).get();

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(image.getMimeType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION
                            , "attachment:filename=\"" + image.getDataName() + "\"")
                    .body(new ByteArrayResource(image.getData64()));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "**ERROR**  --  image! : " +ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not download the file: %s!", image.getDataName()));
        }

    }


    /**
     * Upload User unique Image
     * @param userEmail
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/uploadUserImage/{userEmail}")
    public ResponseEntity<String> uploadUserFile(@PathVariable String userEmail, @RequestParam("file") MultipartFile file)
            throws Exception {
        LOGGER.log(Level.INFO, "**START** - upload user image");
        try {
            if (file == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(String.format("You must select a file for uploading"));
            }
            imageService.saveFile(userEmail, file);
            return ResponseEntity.status(HttpStatus.CREATED).body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "**ERROR**  --  image! : " +ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }

    /**
     * Upload multipple image of establisament
     * @param establishementName
     * @param files
     * @return
     * @throws Exception
     */
    @PostMapping("/uploadEstablishementImages/{establishementName}")
    public ResponseEntity<String> uploadEstablishmentFile(@PathVariable String establishementName, @RequestParam("file") MultipartFile[] files)
            throws Exception {
        LOGGER.log(Level.INFO, "**START** - upload establishment images");
        ResponseEntity<String> result = null;
        try {
            if (files == null || files.length == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(String.format("You must select a file for uploading"));
            }

            imageService.saveMultipleFile(establishementName, files);
            for (MultipartFile file : files) {
                result =  ResponseEntity.status(HttpStatus.CREATED).body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
                return result;
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "**ERROR**  --  image! : " +ex.getMessage(), ex);
            for (MultipartFile file : files) {
                result = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
            }
        }
        //return ResponseEntity.status(HttpStatus.OK).body(String.format("All File uploaded successfully: %s", result));
        return result;
    }

    /**
     * GET unique image of user
     * @param email
     * @return
     * @throws Exception
     */
    @GetMapping("/getUserImage/{email}")
    public ResponseEntity<?> getUserImage(@PathVariable String email)
            throws Exception {
        LOGGER.log(Level.INFO, "**START** - Get user image");
        Image image = null;
        try {
            image = imageService.getFile(email).get();

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(image.getMimeType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION
                            , "attachment:filename=\"" + image.getDataName() + "\"")
                    .body(new ByteArrayResource(image.getData64()));
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "**ERROR**  --  image! : " +ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not download the file: %s!", image));
        }

    }

    /**
     * Get list of image for the establishment
     * @param name
     * @return
     * @throws Exception
     */
    @GetMapping("/getEstablishmentImage/{name}")
    public List<Image> getEstablishmentImage(@PathVariable String name)
            throws Exception{
        LOGGER.log(Level.INFO, "**START** - Get list establishment images");
        try {
            List<Image> images = imageService.getEstablishmentFiles(name)
                    .stream()
                    .map(this::mapToImage)
                    .collect(Collectors.toList());

            System.out.println(images);

            return (List<Image>) images;

        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "**ERROR**  --  image! : " +ex.getMessage(), ex);
            return (List<Image>) ResponseEntity.status(HttpStatus.BAD_REQUEST).body("**ERROR** -- Image can't get list of images associate to the establishment");

        }
    }


    /**
     * Delete user image
     * @param id
     * @param email
     * @return
     * @throws Exception
     */
    @DeleteMapping("/{id}/{email}")
    public ResponseEntity<String> deleteFile(@PathVariable int id, @PathVariable String email)
            throws Exception {
        LOGGER.log(Level.INFO, "**START** - Delete user image");
        try {
            imageService.deleteUserImage(id, email);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Image has been delete and default image set");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "**ERROR**  --  image! : " +ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("**ERROR** -- Image can't be delete");
        }
    }

    /**
     * Send an array of id to be delete
     * @param id of image
     * @param name of establishment
     * @return action status
     * @throws Exception
     */

    @DeleteMapping("/establishment/delete/{name}")
    public ResponseEntity<String> deleteEstablishmentFile(@RequestParam int[] id, @PathVariable String name)
            throws Exception {
        LOGGER.log(Level.INFO, "**START** - Delete establishment images");
        try {
            if (id == null || id.length == 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("**ERROR** -- You must select at least one image id for deleting");
            }
            imageService.deleteEstablishmentImage(id, name);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Image associate to establishment has been delete");
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, "**ERROR**  --  image! : " +ex.getMessage(), ex);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("**ERROR** -- Image can't be delete");
        }
    }
}
