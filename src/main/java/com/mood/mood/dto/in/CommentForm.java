package com.mood.mood.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentForm {
    @NotBlank(message = "Veuillez renseigner un titre pour votre commentaire.")
    @Size(min = 1, max = 50, message = "Votre titre est trop long. {max} caractères maximum.")
    private String title;

    @NotBlank(message = "Veuillez écrire le corps de votre commentaire.")
    @Size(min = 10, message = "Votre commentaire est trop court.")
    private String content;

    private int groupType;
}
