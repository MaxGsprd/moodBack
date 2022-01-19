package com.mood.mood.model;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Table(name="images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private int id;

    @NonNull
    @Column(nullable = false)
    private String dataName;

    @Lob
    @NonNull
    @Column(nullable = false)
    @Basic(fetch = FetchType.LAZY)
    private byte[] data64;

    @NonNull
    @Column(nullable = false)
    private long sizeImage;

    @NonNull
    @Column(nullable = false, length = 300)
    private String mimeType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public byte[] getData64() {
        return data64;
    }

    public void setData64(byte[] data64) {
        this.data64 = data64;
    }

    public long getSizeImage() {
        return sizeImage;
    }

    public void setSizeImage(long sizeImage) {
        this.sizeImage = sizeImage;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}