package com.mood.mood.controller;

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
import java.util.stream.Collectors;

/**
 * Cette classe contient les réponses HTTP liées aux images
 */
@RestController
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping("/test")
    public String getTest() {
        return "test2";
    }

    @GetMapping("/")
    public List<Image> get(Model model) {
        List<Image> images = imageService.getFiles()
                .stream()
                .map(this::mapToImage)
                .collect(Collectors.toList());
        return images;
    }
    private Image mapToImage(Image imageEntity) {
        String downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/files/")
                .path(imageEntity.getDataName())
                .toUriString();
        Image fileResponse = new Image();
        fileResponse.setId(imageEntity.getId());
        fileResponse.setDataName(imageEntity.getDataName());
        fileResponse.setMimeType(imageEntity.getMimeType());

        return fileResponse;
    }

    @PostMapping("/uploadFiles/{userEmail}")
    public ResponseEntity<String> uploadFile(@PathVariable String userEmail, @RequestParam("file") MultipartFile file)
            throws Exception {
        try {
            imageService.saveFile(userEmail, file);
            return ResponseEntity.status(HttpStatus.CREATED).body(String.format("File uploaded successfully: %s", file.getOriginalFilename()));
        } catch (Exception ex) {
            //throw new Exception(ex.getMessage(), ex.getCause());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not upload the file: %s!", file.getOriginalFilename()));
        }
    }

    @GetMapping("/downloadFile/{email}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String email)
            throws Exception{
        try {
        Image image = imageService.getFile(email).get();

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(image.getMimeType()))
                    .header(HttpHeaders.CONTENT_DISPOSITION
                            , "attachment:filename=\"" + image.getDataName() + "\"")
                    .body(new ByteArrayResource(image.getData64()));
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
            /*return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(String.format("Could not download the file: %s!", image));*/
        }

    }

    @DeleteMapping("/{id}/{email}")
    public ResponseEntity<String> deleteFile(@PathVariable int id, @PathVariable String email)
            throws Exception {
        try {
            imageService.deleteImage(id, email);
            return ResponseEntity.status(HttpStatus.CREATED).body("Image has been delete and default image set");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
    }
}
