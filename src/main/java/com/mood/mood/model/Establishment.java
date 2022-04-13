package com.mood.mood.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data //  annotation Lombok pas besoin de getters & setters Lombok le fait pour nous
@Entity // annotation qui indique que la classe correspond à une entité JPA donc une  table de la base de données.
@Table(name="establishment") //indique le nom de la table associée.
@NoArgsConstructor //lombok annotation generate constructor without arguments
@AllArgsConstructor //lombok annotation generate constructor with arguments for us
@RequiredArgsConstructor
public class Establishment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NonNull
    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = true, columnDefinition = "Text")
    private String description;

    /**
     * Establishment status
     * 0:submitted
     * 1:accepted
     */
    @NonNull
    @Column(nullable = false)
    private Boolean status;

    @OneToOne
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JoinColumn(name = "localisation_id")
    private Localisation localisation;

    @NonNull
    @ManyToOne
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy="establishment")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private List<EstablishmentImage> images;

    @OneToMany(mappedBy="establishment")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private List<Note> notes;

    @OneToMany(mappedBy="establishment")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private List<Comment> comments;

    public void addImage(EstablishmentImage image) {
        this.images.add(image);
    }

    public void addComment(Comment comment) {
        if(this.comments == null) this.comments = new ArrayList<>();
        this.comments.add(comment);
    }

    public void addNote(Note note) {
        if(this.notes == null) this.notes = new ArrayList<>();
        this.notes.add(note);
    }
}
