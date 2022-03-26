package com.mood.mood.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvitationEvenement extends Invitation {

    @Column
    private LocalDate invitationDate;

    @ManyToOne
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JoinColumn(name = "establishment_id")
    private Establishment establishment;

    public InvitationEvenement(User organizer, Group group, User receiver, LocalDate invitationDate, Establishment establishment, int status) {
        super(organizer, group, receiver, status);
        this.invitationDate = invitationDate;
        this.establishment = establishment;
    }

    @PrePersist
    public void preSave() {
        setInvitationDate(LocalDate.now());
    }
}
