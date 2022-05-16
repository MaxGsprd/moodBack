package com.mood.mood.dto.in;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentLocalisationSearchForm {

    private LocalisationForm localisationForm;

    private int distance;
}
