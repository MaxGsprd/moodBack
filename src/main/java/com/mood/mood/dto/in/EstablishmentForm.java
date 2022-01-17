package com.mood.mood.dto.in;

import com.mood.mood.model.Category;
import com.mood.mood.model.Comment;
import com.mood.mood.model.Note;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

//    @Pattern(regexp = "[0-9]+", message = "Veuillez saisir des chiffres valides.")
//    private String addressNumber;
//
//    @NotBlank(message = "Veuillez renseigner le nom de rue, avenue, boulevard etc.")
//    @Size(max = 100, message = "L'adresse renseignée est est trop longue. {max} caractères maximum.")
//    private String addressName;
//
//    @NotBlank(message = "Veuillez saisir un code postal.")
//    @Size(min= 5, max = 5, message = "Veuillez saisir un format de code postal correct.")
//    @Pattern(regexp = "[0-9]+", message = "Veuillez saisir des chiffres valides.")
//    private String postalCode;
//
//    @NotBlank(message = "Veuillez saisir une ville")
//    @Size(max = 100, message = "Le nom de la ville est trop long")
//    private String ciy;
//
    @NotNull(message = "Veuillez sélectionner une catégorie pour l'établissement.")
    private int category;


}
