package com.mood.mood.dto.out;

import com.mood.mood.model.Category;
import com.mood.mood.model.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Cette classe contient l'affichage des établissement'.
 * DTO OUT.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentDetails {

    private int id;
    private String name;
    private String description;
    private LocalisationDetails address;
    private List<Image> images;
    private NotesAverage note;
    private List<CommentDetails> comments;
    private Category category;
}
