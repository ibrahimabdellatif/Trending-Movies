package com.ibrahim.trendingmovies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieAPI {

    // https://api.themoviedb.org/3/ >> is base URL
    //trending/movie/day >> is relative URL
//    @GET("trending/movie/day")
//    Call<List<Movie>> getMovie(@Query("api_key") String api_key , @Query("page_number") int page_number);


    @GET("trending/movie/day")
    Call<Results> getResults(@Query("api_key") String api_key , @Query("page_number") int page_number);

    /**
     * this part is just for testing
     */
    //base url >> https://jsonplaceholder.typicode.com/
    // alternative url >> posts
    @GET("posts")
    Call<List<Test>> getPosts();

}
