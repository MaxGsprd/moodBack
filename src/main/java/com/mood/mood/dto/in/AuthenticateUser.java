package com.mood.mood.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticateUser {
    @NotBlank(message = "Veuillez renseigner votre adresse email.")
    @Email(message = "Votre adresse email doit être valide.")
    private String email;

    @NotBlank(message = "Veuillez renseigner votre mot de passe.")
    private String password;
}
