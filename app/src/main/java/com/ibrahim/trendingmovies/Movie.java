package com.ibrahim.trendingmovies;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Movie implements Serializable {
    private int id;
    @SerializedName("image")
    private String imagePath;
    @SerializedName("original_title")
    private String original_title;
    @SerializedName("release_date")
    private String release_date;
    @SerializedName("rating")
    private double rating;
    @SerializedName("overview")
    private String overview;
    private List<Movie> movies;

    //more unused variable {it's optional in api}
    private boolean adult;
    private List<Integer> genre_ids;
    private String original_language;
    private String title;
    private String backdrop_path;
    private double popularity;
    private int vote_count;
    private boolean video;
    private double vote_average;
    private int total_pages;
    private int total_results;


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
        return original_title;
    }

    public String getDate() {
        return release_date;
    }

    public double getRating() {
        return rating;
    }

    public String getOverview() {
        return overview;
    }

    public boolean isAdult() {
        return adult;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public double getPopularity() {
        return popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public boolean isVideo() {
        return video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }
}
