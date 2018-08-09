package be.evoliris.formation.hypesql.models;

public class Movie {
    private long id;
    private String title;
    private String sypnosis;
    private String director;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getSypnosis() {
        return sypnosis;
    }
    public void setSypnosis(String sypnosis) {
        this.sypnosis = sypnosis;
    }
    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }
}
