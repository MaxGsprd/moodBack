package com.mood.mood.dto.in;

import com.mood.mood.model.Comment;
import com.mood.mood.model.Note;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EstablishmentUserForm extends EstablishmentForm {

    private Note note;
    private CommentForm comment;
}
