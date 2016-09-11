package com.softserve.edu.delivery.domain;
/**
 * Author - Kurdiukov Taras.
 */
import javax.persistence.*;

@Entity
@Table(name = "DOCUMENT")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private Long id;

    @Column(name = "series")
    private String series;

    @Column(name = "number")
    private String number;

    @Column(name = "front_photo_url")
    private String front_photo_url;

    @Column(name = "back_photo_url")
    private String back_photo_url;

    public Document() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getFront_photo_url() {
        return front_photo_url;
    }

    public void setFront_photo_url(String front_photo_url) {
        this.front_photo_url = front_photo_url;
    }

    public String getBack_photo_url() {
        return back_photo_url;
    }

    public void setBack_photo_url(String back_photo_url) {
        this.back_photo_url = back_photo_url;
    }
}
