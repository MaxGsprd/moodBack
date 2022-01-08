package com.mood.mood.dto.out;

import com.mood.mood.model.EstablishmentImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

/**
 * Cette classe contient l'affichage des établissement'.
 * DTO OUT.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentDetails {
    @NonNull
    private String name;
    private String description;
//    @NonNull
//    private LocalisationDetails address;
//    private List<EstablishmentImage> images;
//    private NotesAverage note;
//    private List<CommentDetails> comments;
}
