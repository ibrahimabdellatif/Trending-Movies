package com.ibrahim.trendingmovies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieAPI {

    // https://api.themoviedb.org/3/ >> is base URL
    //trending/movie/day >> is relative URL
    // it's page in query not page_number
    @GET("trending/movie/day")
    Call<Results> getResults(@Query("api_key") String api_key, @Query("page") int page_number);

}
