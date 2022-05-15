package com.mood.mood.dto.out;

import com.mood.mood.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDetails {
    @NonNull
    private String title;
    @NonNull
    private String content;
    private String groupType;
    @NonNull
    private String createdDate;
    @NonNull
    private User user;
}
