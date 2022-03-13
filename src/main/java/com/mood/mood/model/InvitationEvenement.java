package com.mood.mood.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvitationEvenement extends Invitation {

    @Column
    private LocalDateTime invitationDate;

    @ManyToOne
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JoinColumn(name = "establishment_id")
    private Establishment establishment;

    public InvitationEvenement(User organizer, Group group, User receiver, LocalDateTime invitationDate, Establishment establishment, @NonNull int status) {
        super(organizer, group, receiver, status);
        this.invitationDate = invitationDate;
        this.establishment = establishment;
    }

    @PrePersist
    public void preSave() {
        setInvitationDate(LocalDateTime.now());
    }
}
