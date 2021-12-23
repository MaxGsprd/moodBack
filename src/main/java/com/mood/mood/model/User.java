package com.mood.mood.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name="users")
public class User {

    @Id
    @GeneratedValue
    private int id;

    @NonNull
    @Column(nullable = false, length = 50)
    private String name;

    @NonNull
    @Column(nullable = false, length = 50)
    private String firstname;

    @NonNull
    @Column(nullable = false)
    private LocalDate birthdate;

    @NonNull
    @Column(nullable = false, length = 255)
    private String email;

    @NonNull
    @Column(nullable = false, length = 300)
    private String password;

    @NonNull
    @Column(nullable = false, length = 14)
    private String phone;

    @NonNull
    @ManyToOne
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JoinColumn(name = "localisation_id")
    private Localisation localisation;

    @NonNull
    @OneToOne
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JoinColumn(name = "role_id")
    private Role role;

    @NonNull
    @ManyToOne
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JoinColumn(name = "mood_id")
    private Category mood;

    @OneToOne
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JoinColumn(name = "image_id")
    private UserImage image;

    @ManyToMany
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JoinTable( name = "users_groups",
            joinColumns = @JoinColumn( name = "user_id" ),
            inverseJoinColumns = @JoinColumn( name = "group_id" ))
    private List<Group> groups;

    @OneToMany(mappedBy="user")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private List<Note> notes;

    @OneToMany(mappedBy="user")
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    private List<Comment> comments;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public Localisation getLocalisation() {
        return localisation;
    }

    public Role getRole() {
        return role;
    }

    public Category getMood() {
        return mood;
    }

    public UserImage getImage() {
        return image;
    }

    public List<Group> getGroups() {
        return groups;
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

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLocalisation(Localisation localisation) {
        this.localisation = localisation;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setMood(Category mood) {
        this.mood = mood;
    }

    public void setImage(UserImage image) {
        this.image = image;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void addNote(Note note) {
        this.notes.add(note);
    }

    public void addGroup(Group group) {
        this.groups.add(group);
    }
}