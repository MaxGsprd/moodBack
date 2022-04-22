package com.mood.mood.dto.in;

import com.mood.mood.model.Category;
import com.mood.mood.model.Comment;
import com.mood.mood.model.Note;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentForm {

    @NotBlank(message = "Veuillez renseigner le nom de l'établissement.")
    @Size(min = 1, max = 100, message = "Le nom de l'établissement est trop long. {max} caractères maximum.")
    private String name;

    @NotBlank(message = "Veuillez renseigner une description pour l'établissement.")
    @Size(min = 1, max = 500, message = "La description de l'établissement est trop longue. {max} caractères maximum.")
    private String description;

    private LocalisationForm localisationForm;

    private MultipartFile[] image;

    @NotNull(message = "Veuillez sélectionner une catégorie pour l'établissement.")
    private int category;


}
