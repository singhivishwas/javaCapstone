package com.example.moviecatalogue.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class FavoriteMovie {

    @Id
    private int id;
    private String title;
    private String posterPath;
    private String releaseDate;
    private double rating;

    // Getters & setters...

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getPosterPath() { return posterPath; }
    public void setPosterPath(String posterPath) { this.posterPath = posterPath; }

    public String getReleaseDate() { return releaseDate; }
    public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
}
