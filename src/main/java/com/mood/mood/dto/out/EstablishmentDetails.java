package com.mood.mood.dto.out;

import com.mood.mood.model.EstablishmentImage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentDetails {
    @NonNull
    private String name;
    private String Description;
    @NonNull
    private LocalisationDetails address;
    private List<EstablishmentImage> images;
    private NotesAverage note;
    private List<CommentDetails> comments;
}
