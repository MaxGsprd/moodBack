package com.mood.mood.service;

import com.mood.mood.exceptions.StorageFileNotFoundException;
import com.mood.mood.model.*;
import com.mood.mood.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;


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
    @Autowired
    private UserImageRepository userImageRepository;

    @Value("${image.default.name}")
    public String defaultImageName;


    @Override
    public void saveFile(String email, MultipartFile file) throws Exception {

        User user = userRepository.findByEmail(email);

        String dataName = StringUtils.cleanPath(file.getOriginalFilename());
        String dataType = file.getContentType();
        byte[] data64 = file.getBytes();
        Long size = file.getSize();

        try {
            // Si l'utilisateur avait déjà une image hors image par défaut
            if (((User) user).getImage() != null || !user.getImage().getDataName().equals(defaultImageName)) {
                this.deleteUserImage(user.getImage().getId(), user.getEmail());
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
            User user = userRepository.findByEmail(email);

            if (user.getImage() == null) return null;

            return imageRepository.findById(user.getImage().getId());

    }


    @Override
    public Resource loadAsResource(String filename) {
        try {
            Image img = imageRepository.findByDataName(filename);
            Path file = (Path) img;
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            }
            else {
                throw new StorageFileNotFoundException("Could not read file: " + filename);

            }
        }
        catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

    @Override
    public List<Image> getEstablishmentFiles(String name) {

        Establishment establishment = establishementRepository.findByNameContaining(name);
        if (establishment.getImages() == null) return null;
        List<Image> images = establishementImageRepository.findByEstablishment(establishment);

        return images;
    }

    @Override
    public List<Image> getFiles() {
        return imageRepository.findAll();
    }

    /**
     *
     * @param id
     * @return all image of establishment
     */
    public Optional<EstablishmentImage> getFileById(int id) {
        return establishementImageRepository.findById(id);
    }

    public Optional<Image> getImageById(int id) {
        return imageRepository.findById(id);
    }

    /**
     * Efface l'image de l'utilisateur et met l'image par defaut
     * @param id
     * @param email
     * @return
     * @throws Exception
     */
    @Override
    public boolean deleteUserImage(int id, String email) throws Exception {
        try {
            Image image = userImageRepository.findById(id).orElse(null);
            User user = userRepository.findByEmail(email);
            Image defaultUserImage = imageRepository.findByDataName(defaultImageName);

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

    @Override
    public boolean deleteEstablishmentImage(int id, String name) throws Exception {
        try {
            Image image = establishementImageRepository.findById(id).orElse(null);

            Establishment establishment = (Establishment) establishementRepository.findByNameLikeIgnoreCase(name);

            imageRepository.deleteById(image.getId());

            return true;

        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
    }

    @Override
    public boolean deleteEstablishmentImage(int[] ids, String name) throws Exception {
        try {
            for (int id: ids) {


                Image image = establishementImageRepository.findById(id).orElse(null);

                Establishment establishment = (Establishment) establishementRepository.findByNameLikeIgnoreCase(name);

                imageRepository.deleteById(image.getId());

                return true;
            }
        } catch (Exception ex) {
            throw new Exception(ex.getMessage(), ex.getCause());
        }
        return false;
    }


}
