package com.mood.mood.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class InvitationEvenement extends Invitation {

    @NonNull
    @Column(nullable = false)
    private LocalDateTime invitationDate;

    /**
     * 0:submitted
     * 1:accepted
     * 2:refused
     * 3:cancelled
     */
    @NonNull
    @Column(nullable = false)
    private int status;

    @ManyToOne
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JoinColumn(name = "establishment_id")
    private Establishment establishment;

    public InvitationEvenement(User organizer, Group group, User destinataire, @NonNull LocalDateTime invitationDate, @NonNull int status, Establishment establishment) {
        super(organizer, group, destinataire);
        this.invitationDate = invitationDate;
        this.status = status;
        this.establishment = establishment;
    }

    public LocalDateTime getInvitationDate() {
        return invitationDate;
    }

    public void setInvitationDate(LocalDateTime invitationDate) {
        this.invitationDate = invitationDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }
}
