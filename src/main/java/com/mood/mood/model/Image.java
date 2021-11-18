package com.mood.mood.model;

import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@RequiredArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor
@Table(name="images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(nullable = false, columnDefinition = "Text")
    private String dataImage64;

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

    public String getDataImage64() {
        return dataImage64;
    }

    public void setDataImage64(String dataImage64) {
        this.dataImage64 = dataImage64;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }
}