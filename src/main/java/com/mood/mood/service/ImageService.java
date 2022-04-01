package com.mood.mood.service;

import com.mood.mood.repository.EstablishementImageRepository;
import com.mood.mood.repository.EstablishmentRepository;
import com.mood.mood.repository.ImageRepository;
import com.mood.mood.repository.UserRepository;
import com.mood.mood.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
public class ImageService implements IImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EstablishmentRepository establishementRepository;
    @Autowired
    private EstablishementImageRepository establishementImageRepository;


    @Override
    public void saveFile(String email, MultipartFile file) throws Exception {

        User user = userRepository.findByEmail(email);

        String dataName = StringUtils.cleanPath(file.getOriginalFilename());
        String dataType = file.getContentType();
        byte[] data64 = file.getBytes();
        Long size = file.getSize();

        try {
            // Si l'utilisateur avait déjà une image hors image par défaut
            if (((User) user).getImage() != null || !user.getImage().getDataName().equals("default_image.png")) {
                this.deleteImage(user.getImage().getId(), user.getEmail());
            }

            UserImage userImage = new UserImage();
            userImage.setDataName(dataName);
            userImage.setData64(data64);
            userImage.setMimeType(dataType);
            userImage.setSizeImage(size);
            userImage.setUser(user);

            imageRepository.save(userImage); // save Image

            /**
             * update user && save new image
             */
            user.setImage(userImage);
            userRepository.save(user);

        } catch (Exception e) {
            e.printStackTrace();
            throw new DataIntegrityViolationException(e.getMessage());
        }


    }

    public void saveMultipleFile(String establishementName, MultipartFile[] files) throws IOException {

        if (files == null || files.length == 0) {
            throw new RuntimeException("You must select at least one file for uploading");
        }
        for (MultipartFile file : files) {

            Establishment establishment = establishementRepository.findByNameContaining(establishementName);

            String dataName = StringUtils.cleanPath(file.getOriginalFilename());
            String dataType = file.getContentType();
            byte[] data64 = file.getBytes();
            Long size = file.getSize();

            try {
                EstablishmentImage establishmentImage =  new EstablishmentImage();
                establishmentImage.setDataName(dataName);
                establishmentImage.setData64(data64);
                establishmentImage.setMimeType(dataType);
                establishmentImage.setSizeImage(size);
                establishmentImage.setEstablishment(establishment);

                imageRepository.save(establishmentImage);
            } catch (Exception e) {
                e.printStackTrace();
                throw new DataIntegrityViolationException(e.getMessage());
            }

        }
    }

    /**
     * @param email of user selected
     * @return image from BDD
     */
    @Override
    public Optional<Image> getFile(String email) {
        if (email.contains("@")) {
            User user = userRepository.findByEmail(email);

            if (user.getImage() == null) return null;

            return imageRepository.findById(user.getImage().getId());
        } else {
            Establishment establishment = establishementRepository.findByNameContaining(email);
            if (establishment.getImages() == null) return null;

            return establishementImageRepository.findByEstablishment(establishment);
        }
    }

    @Override
    public List<Image> getFiles() {
        return imageRepository.findAll();
    } // not working at all

    @Override
    public boolean deleteImage(int id, String email) throws Exception {
        try {
            Image image = imageRepository.findById(id).orElse(null);
            User user = userRepository.findByEmail(email);
            Image defaultUserImage = imageRepository.findByDataName("default_image.png");

            /**
             * Si l'image est egal a null ou a l'image par défaut, ne pas effacé l'image
             */
            if (image == null || image == defaultUserImage) {
                return false;
            } else {
                /**
                 * @param SECUTIRY Sécurité supplémentaire pour le front.
                 *
                 * pour le front. mettre l'image de l'utilisateur a null avant de l'éffacé
                 */
                user.setImage(null);

                imageRepository.deleteById(image.getId());

                /**
                 * Une fois l'image éffacer. Remettre l'image par défaut
                 */
                if (user.getImage() == null) {

                    Image defaultImage = defaultUserImage;

                    user.setImage((UserImage) defaultImage);
                    userRepository.save(user);
                }

                return true;
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
    }


}
