package com.mood.mood.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentEditorform extends EstablishmentForm {

    @NotBlank(message = "Veuillez renseigner la description de l'établissement.")
    @Size(min = 50, message = "La description de l'établissement est trop courte")
    private String description;

    private List<MultipartFile> images;
}
