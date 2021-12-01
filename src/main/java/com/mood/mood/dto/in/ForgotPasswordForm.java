package com.mood.mood.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForgotPasswordForm {
    @NotBlank(message = "Veuillez renseigner votre adresse email.")
    @Email(message = "Votre adresse email doit être valide.")
    private String email;

    @NotBlank(message = "Veuillez renseigner votre mot de passe.")
    @Size(min = 10, message = "Le mot de passe doit être de dix caractères minimum.")
    private String password;

    @NotBlank(message = "Veuillez renseigner votre mot de passe.")
    @Size(min = 10, message = "Le mot de passe doit être de dix caractères minimum.")
    private String confirmPassword;
}
