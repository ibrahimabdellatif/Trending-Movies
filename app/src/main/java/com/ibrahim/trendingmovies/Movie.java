package com.ibrahim.trendingmovies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    private int id;
    @SerializedName("image")
    private String imagePath;
    @SerializedName("name")
    private String name;
    @SerializedName("date")
    private String date;
    @SerializedName("rating")
    private double rating;
    @SerializedName("overview")
    private String overview;
    private List<Movie> movies;

    public Movie(List<Movie> movies) {
        this.movies = movies;
    }


    public List<Movie> getMovies() {
        return movies;
    }


    public String getImagePath() {
        return "\"https://image.tmdb.org/t/p/w500/\"" + imagePath;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public double getRating() {
        return rating;
    }

    public String getOverview() {
        return overview;
    }
}
