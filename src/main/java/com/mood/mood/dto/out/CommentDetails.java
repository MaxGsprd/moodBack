package com.mood.mood.dto.out;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

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
}
