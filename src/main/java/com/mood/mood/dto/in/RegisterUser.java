package com.mood.mood.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUser {
    @NotBlank(message = "Veuillez renseigner votre nom.")
    @Size(min = 1, max = 100, message = "Votre nom est trop long. {max} caractères maximum.")
    private String name;

    @NotBlank(message = "Veuillez renseigner votre prenom.")
    @Size(min = 1, max = 100, message = "Votre prenom est trop long. {max} caractères maximum.")
    private String firstname;

    @NotBlank(message = "Veuillez renseigner votre mot de passe.")
    @Size(min = 10, message = "Le mot de passe doit être de dix caractères minimum.")
    private String password;

    @NotBlank(message = "Veuillez renseigner votre mot de passe.")
    private String confirmPassword;

    @NotNull(message = "Veuillez renseigner votre date de naissance.")
    private LocalDate birthdate;

    @Pattern(regexp = "[0-9]+", message = "Veuillez saisir des chiffres valides.")
    private String addressNumber;

    @NotBlank(message = "Veuillez renseigner le nom de rue, avenue, boulevard etc.")
    @Size(max = 100, message = "L'adresse renseignée est est trop longue. {max} caractères maximum.")
    private String addressName;

    @NotBlank(message = "Veuillez saisir un code postal.")
    @Size(min= 5, max = 5, message = "Veuillez saisir un format de code postal correct.")
    @Pattern(regexp = "[0-9]+", message = "Veuillez saisir des chiffres valides.")
    private String postalCode;

    @NotBlank(message = "Veuillez saisir une ville")
    @Size(max = 100, message = "Le nom de la ville est trop long")
    private String city;

    @NotBlank(message = "Veuillez renseigner votre adresse email.")
    @Email(message = "Votre adresse email doit être valide.")
    @Size(min = 1, max = 100, message = "Votre adresse email est trop long. {max} caractères maximum.")
    private String email;

    @NotBlank(message = "Veuillez renseigner votre n° de téléphone")
    @Pattern(regexp = "[0-9]+", message = "Format du n° de tel : 0102030405 ou 01 02 03 04 05.")
    @Size(min = 10, max = 14, message = "Le n° de téléphone renseigné est trop court ou trop long. " +
            "Format du n° de tel : 0102030405 ou 01 02 03 04 05.")
    private String phone;

    @NotNull(message = "Veuillez sélectionner votre préférence.")
    private int mood;

    private MultipartFile image;

    public boolean checkPassword() {
        return this.password == this.confirmPassword;
    }

}
