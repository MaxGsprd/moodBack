package com.mood.mood.model;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@DiscriminatorValue("EI")
public class EstablishmentImage extends Image{

    @ManyToOne
    @JoinColumn(name = "establishment_id")
    private Establishment establishment;

    public EstablishmentImage(String dataName, byte[] data64, long sizeImage, String mimeType, Establishment establishment) {
        super(dataName, data64, sizeImage, mimeType);
        this.establishment = establishment;
    }

    public Establishment getEstablishment() {
        return establishment;
    }

    public void setEstablishment(Establishment establishment) {
        this.establishment = establishment;
    }

}
