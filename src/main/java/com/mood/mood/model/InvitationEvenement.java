package com.mood.mood.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Data
@AllArgsConstructor
//@RequiredArgsConstructor
@NoArgsConstructor
public class InvitationEvenement extends Invitation {

    @Column
    private LocalDateTime invitationDate;

//    /**
//     * 0:submitted
//     * 1:accepted
//     * 2:refused
//     * 3:cancelled
//     */
//    @NonNull
//    @Column(nullable = false)
//    private int status;

    @ManyToOne
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id")
    @JoinColumn(name = "establishment_id")
    private Establishment establishment;

    public InvitationEvenement(User organizer, Group group, User destinataire, LocalDateTime invitationDate, Establishment establishment, @NonNull int status) {
        super(organizer, group, destinataire, status);
        this.invitationDate = invitationDate;
        this.establishment = establishment;
    }
}
