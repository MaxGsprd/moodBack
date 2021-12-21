package com.mood.mood.model;

import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Entity
@DiscriminatorValue("IG")
@NoArgsConstructor
public class InvitationGroup extends Invitation {

    /**
     * 0:submitted
     * 1:accepted
     * 2:refused
     * 3:cancelled
     */
    @NonNull
    @Column(nullable = false)
    private int status;

    public InvitationGroup(User organizer, Group group, User destinataire, @NonNull int status) {
        super(organizer, group, destinataire);
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
