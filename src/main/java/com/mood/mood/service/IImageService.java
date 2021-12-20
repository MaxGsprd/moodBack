package com.mood.mood.service;

import com.mood.mood.model.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

public interface IImageService {
    Image saveFile(String email, MultipartFile file) throws Exception;
    Optional<Image> getFile(String email);
    List<Image> getFiles();
    boolean deleteImage(int id, String email) throws Exception;
}
