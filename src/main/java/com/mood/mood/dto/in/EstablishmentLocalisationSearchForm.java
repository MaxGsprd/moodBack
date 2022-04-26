package com.mood.mood.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentLocalisationSearchForm {

    private LocalisationForm localisationForm;

    //@Pattern(regexp = "[0-9]", message = "Veuillez saisir des chiffres valides.")
    //@DecimalMin(value = "10.0", inclusive = false)
    //@Digits(message="Veuillez saisir des chiffres valides.", fraction = 2, integer = 3)
    private int distance;
}
