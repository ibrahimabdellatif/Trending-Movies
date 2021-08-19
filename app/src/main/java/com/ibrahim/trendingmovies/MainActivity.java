package com.ibrahim.trendingmovies;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
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
    //    private List<Movie> moviesList = new ArrayList<>();
//    private Movie movieTest;
    private Retrofit retrofit;
    private TextView tvTestPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTestPosts = findViewById(R.id.tv_test_posts);

        Gson gson = new GsonBuilder().serializeNulls().create();
        retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        movieAPI = retrofit.create(MovieAPI.class);
        getMovie();


    }

    private void getMovie() {
        Call<List<Movie>> call = movieAPI.getMovie(API_KEY);

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                if (!response.isSuccessful()) {
                    //Toast.makeText(MainActivity.this, "Code: " + response.code(), Toast.LENGTH_SHORT).show();
                    tvTestPosts.setText("response: " + response.code());
                    return;
                }
                List<Movie> movies = response.body();
                for (Movie movie : movies) {
                    String content = "";
                    content += "Title" + movie.getName();
                    content += "date" + movie.getDate();
                    content += "imagePath" + movie.getImagePath();
                    content += "overview" + movie.getOverview();
                    content += "rating" + movie.getRating();

                    tvTestPosts.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {
                tvTestPosts.setText("failure: " + t.getMessage());
            }
        });
    }

    private void getPosts() {
        Call<List<Test>> call = movieAPI.getPosts();

        call.enqueue(new Callback<List<Test>>() {
            @Override
            public void onResponse(Call<List<Test>> call, Response<List<Test>> response) {
                if (!response.isSuccessful()) {
                    tvTestPosts.setText("code : " + response.code());
                    return;
                }
                List<Test> tests = response.body();
                for (Test test : tests) {
                    String content = "";
                    content += "ID: " + test.getId() + "\n";
                    content += "UserId: " + test.getUserId() + "\n";
                    content += "Title: " + test.getTitle() + "\n";
                    content += "Text: " + test.getBody() + "\n\n";

                    tvTestPosts.append(content);
                }

            }

            @Override
            public void onFailure(Call<List<Test>> call, Throwable t) {
                tvTestPosts.setText(t.getMessage());
            }
        });

    }


    void setRecyclerView() {
        //adapter = new MovieAdapter(moviesList);
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