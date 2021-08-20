package com.ibrahim.trendingmovies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String API_KEY = "e4a04a457c1132a76a9ff703b7f90d1f";
    private int pageNumber = 1;
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private MovieAPI movieAPI;
    private Retrofit retrofit;
    private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Gson gson = new GsonBuilder().serializeNulls().create();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        movieAPI = retrofit.create(MovieAPI.class);
        getMovie();
    }

    private void getMovie() {
        Call<Results> call = movieAPI.getResults(API_KEY, pageNumber);
        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                if (!response.isSuccessful()) {
                    Log.d("on Response", response.code() + "");
                }
                Results results = response.body();
                setRecyclerView(results.getResults());

            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage() + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void setRecyclerView(List<Movie> movies) {
        movieList = movies;
        adapter = new MovieAdapter(movieList, this);
        recyclerView = findViewById(R.id.rv_movie_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickLister(new MovieAdapter.OnMovieListener() {
            @Override
            public void onMovieClick(Movie movieItems) {
                Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this, movieItems.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}