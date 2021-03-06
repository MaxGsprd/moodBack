package com.mood.mood.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@RequiredArgsConstructor
public class Invitation {

    @Id
    @GeneratedValue
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
    @JoinColumn(name = "group_id")
    private Group group;

    @NonNull
    @ManyToOne
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id"
    )
    @JoinColumn(name = "user_id")
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

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getOrganizer() {
        return organizer;
    }

    public void setOrganizer(User organizer) {
        this.organizer = organizer;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }
}
