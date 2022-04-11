package com.mood.mood.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name="invitation")
@NoArgsConstructor
@RequiredArgsConstructor
public class Invitation implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @NonNull
    @ManyToOne
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JoinColumn(name = "organizer_id")
    private User organizer;

    @NonNull
    @ManyToOne
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property="id")
    @JoinColumn(name="group_id")
    private Group group;

    @NonNull
    @ManyToOne
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id"
    )
    @JoinColumn(name="receiver_id")
    private User receiver;

    /**
     * 0:submitted
     * 1:accepted
     * 2:refused
     * 3:cancelled
     */
    @NonNull
    @Column(nullable = false)
    private int status;

}
