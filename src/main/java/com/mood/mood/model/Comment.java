package com.mood.mood.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment") //indique le nom de la table associée.
public class Comment {

    @Id
    @GeneratedValue
    private int id;

    @NonNull
    @Column(nullable = false, length = 50)
    private String title;

    @NonNull
    @Column(nullable = true, columnDefinition = "Text")
    private String content;

//    @NonNull
    @Column(nullable = false)
    private LocalDateTime createdDate;

    /**
     * 0: default
     * : signalé
     */
    @NonNull
    @Column(nullable = false)
    private Boolean status;

    /**
     * 0:solo
     * 1:couple
     * 2:group
     */
    @NonNull
    @Column(nullable = false)
    private int groupType;

    @ManyToOne
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JoinColumn(name = "establishment_id")
    private Establishment establishment;

    @ManyToOne
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private User user;

}
