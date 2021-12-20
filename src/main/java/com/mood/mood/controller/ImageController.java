package com.mood.mood.controller;

import com.mood.mood.model.Image;
import com.mood.mood.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
/**
 * Cette classe contient les réponses HTTP liées à l'image d'un utilisateur
 */
@Controller
@RequestMapping("/image")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping("/")
    public String get(Model model) {
        List<Image> images = imageService.getFiles();
        model.addAttribute("images", images);
        return "images";
    }

    @PostMapping("/uploadFiles/{userEmail}")
    public ResponseEntity<String> uploadFile(@PathVariable String userEmail, @RequestParam("file") MultipartFile file)
            throws Exception {
        try {
            imageService.saveFile(userEmail, file);
            return ResponseEntity.status(HttpStatus.CREATED).body("Image uploaded successfully");
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
    }

    @GetMapping("/downloadFile/{email}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable String email) {
        Image image = imageService.getFile(email).get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getMimeType()))
                .header(HttpHeaders.CONTENT_DISPOSITION
                        , "attachment:filename=\"" + image.getDataName() + "\"")
                .body(new ByteArrayResource(image.getData64()));

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
