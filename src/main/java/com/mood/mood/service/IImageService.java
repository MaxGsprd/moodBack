package com.mood.mood.service;

import com.mood.mood.model.Image;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IImageService {
    void saveFile(String email, MultipartFile file) throws Exception;
    void saveMultipleFile(String establishementName, MultipartFile[] files) throws IOException;
    Optional<Image> getFile(String email);

    Resource loadAsResource(String filename);

    List<Image> getEstablishmentFiles(String name);

    List<Image> getFiles();
    boolean deleteUserImage(int id, String email) throws Exception;

    boolean deleteEstablishmentImage(int id, String name) throws Exception;

    boolean deleteEstablishmentImage(int[] ids, String name) throws Exception;
}
