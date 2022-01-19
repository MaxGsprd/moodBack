package com.mood.mood.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@DiscriminatorValue("UI")
@NoArgsConstructor
public class UserImage extends Image{

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserImage(String dataName, byte[] data64, long sizeImage, String mimeType, User user) {
        super(dataName, data64, sizeImage, mimeType);
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
