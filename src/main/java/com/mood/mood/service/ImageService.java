package com.mood.mood.service;

import com.mood.mood.Repository.EstablishementImageRepository;
import com.mood.mood.Repository.EstablishmentRepository;
import com.mood.mood.Repository.ImageRepository;
import com.mood.mood.Repository.UserRepository;
import com.mood.mood.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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

        String dataName = StringUtils.cleanPath(file.getOriginalFilename());
        String dataType = file.getContentType();
        byte[] data64 = file.getBytes();
        Long size = file.getSize();
        String image64 = Base64.getEncoder().encodeToString(data64);

        if (email.contains("@")) { // si c'est un mail c'est un user sinon un établisement
            User user = userRepository.findByEmail(email);
            try {
                // Si l'utilisateur avait déjà une image hors image par défaut
                /*if (((User) user).getImage() != null && !user.getImage().getDataName().equals("default_image.png")) {
                    this.deleteImage(user.getImage().getId(), user.getEmail());
                }*/

                UserImage userImage = new UserImage();
                userImage.setDataName(dataName);
                userImage.setData64(data64);
                userImage.setMimeType(dataType);
                userImage.setDataImage64(image64);
                userImage.setUser(user);

                imageRepository.save(userImage);

            } catch (Exception e) {
                e.printStackTrace();
                throw new DataIntegrityViolationException(e.getMessage());
            }
        } else {
            Establishment establishment = establishementRepository.findByNameContaining(email);
            try {
                    EstablishmentImage establishmentImage =  new EstablishmentImage();
                    establishmentImage.setDataName(dataName);
                    establishmentImage.setData64(data64);
                    establishmentImage.setMimeType(dataType);
                    establishmentImage.setDataImage64(image64);
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
