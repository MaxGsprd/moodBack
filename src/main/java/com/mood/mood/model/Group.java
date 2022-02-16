package com.mood.mood.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="groups")
public class Group {

    @Id
    @GeneratedValue
    private int id;

    @NonNull
    @Column(nullable = false, length = 50)
    private String title;

    @NonNull
    @Column(nullable = false)
    private LocalDateTime createdDate;

    @NonNull
    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @ManyToMany
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JoinTable( name = "users_groups",
            joinColumns = @JoinColumn( name = "group_id" ),
            inverseJoinColumns = @JoinColumn( name = "user_id" ))
    private List<User> users;


    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public void setUpdatedDate(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User removedUser) {
        this.users.remove(removedUser);
    }
}
