package com.ibrahim.trendingmovies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
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
    public static final String extra_poster = "poster";
    public static final String extra_title = "title";
    public static final String extra_date = "date";
    public static final String extra_rating = "rating";
    public static final String extra_overview = "overview";
    private int pageNumber = 1;
    private RecyclerView recyclerView;
    private MovieAdapter adapter;
    private MovieAPI movieAPI;
    private Retrofit retrofit;
    private List<Movie> movieList;
    private Button btnNextPage;
    private Button btnPreviousPage;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        setRetrofit();
        getMovieByRetrofit(pageNumber);
        setPageNumber();
    }

    private void setRetrofit() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        movieAPI = retrofit.create(MovieAPI.class);
    }

    private void getMovieByRetrofit(int pageNumber) {
        Call<Results> call = movieAPI.getResults(API_KEY, pageNumber);
        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                if (!response.isSuccessful()) {
                    Log.d("on Response", response.code() + "");
                }
                Results results = response.body();
                setRecyclerView(results.getResults());
                progressBar.setVisibility(View.GONE);


            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Please Check Your Internet Connection and Try Again", Toast.LENGTH_LONG).show();
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
                intent.putExtra(extra_poster, movieItems.getImagePath());
                intent.putExtra(extra_title, movieItems.getName());
                intent.putExtra(extra_date, movieItems.getDate());
                intent.putExtra(extra_rating, movieItems.getRating());
                intent.putExtra(extra_overview, movieItems.getOverview());
                startActivity(intent);
            }
        });
    }

    private void setPageNumber() {
        btnNextPage = findViewById(R.id.btn_next);
        btnPreviousPage = findViewById(R.id.btn_previous);

        btnNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pageNumber++;
                getMovieByRetrofit(pageNumber);
                progressBar.setVisibility(View.VISIBLE);
                btnPreviousPage.setVisibility(View.VISIBLE);

            }
        });

        btnPreviousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pageNumber > 1) {
                    pageNumber--;
                    progressBar.setVisibility(View.VISIBLE);

                }
                getMovieByRetrofit(pageNumber);

            }
        });

        btnPreviousPage.setVisibility(View.GONE);
    }


}