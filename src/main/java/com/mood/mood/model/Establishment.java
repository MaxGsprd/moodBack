package com.mood.mood.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.List;

@Data //  annotation Lombok pas besoin de getters & setters Lombok le fait pour nous
@Entity // annotation qui indique que la classe correspond à une entité JPA donc une  table de la base de données.
@Table(name = "establishment") //indique le nom de la table associée.
@NoArgsConstructor //lombok annotation genetate constructor without arguments
@AllArgsConstructor //lombok annotation generate constructor with arguments for us
public class Establishment {

    @Id
    @GeneratedValue
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

   /* public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Boolean getStatus() {
        return status;
    }

    public Localisation getLocalisation() {
        return localisation;
    }

    public Category getCategory() {
        return category;
    }

    public List<EstablishmentImage> getImages() {
        return images;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }

    public void setCategory(Category category) {
        this.category = category;
    }*/

    public void addImage(EstablishmentImage image) {
        this.images.add(image);
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void addNote(Note note) {
        this.notes.add(note);
    }
}
