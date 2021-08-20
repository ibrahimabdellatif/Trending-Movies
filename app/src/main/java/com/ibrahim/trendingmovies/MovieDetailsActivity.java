package com.ibrahim.trendingmovies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import static com.ibrahim.trendingmovies.MainActivity.extra_date;
import static com.ibrahim.trendingmovies.MainActivity.extra_overview;
import static com.ibrahim.trendingmovies.MainActivity.extra_poster;
import static com.ibrahim.trendingmovies.MainActivity.extra_rating;
import static com.ibrahim.trendingmovies.MainActivity.extra_title;

public class MovieDetailsActivity extends AppCompatActivity {

    private ImageView ivPoster;
    private TextView tvTitle;
    private TextView tvReleaseDate;
    private TextView tvRating;
    private TextView tvOverview;
    private String posterUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        ivPoster = findViewById(R.id.iv_movie_poster_details);
        tvTitle = findViewById(R.id.tv_movie_title_details);
        tvReleaseDate = findViewById(R.id.tv_movie_date_details);
        tvRating = findViewById(R.id.tv_movie_rating_details);
        tvOverview = findViewById(R.id.tv_movie_over_view_details);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getData();
    }

    private void getData() {
        Intent data = getIntent();
        posterUrl = data.getStringExtra(extra_poster);
        if (posterUrl != null) {
            Uri image_url = Uri.parse(posterUrl);
            Glide.with(this).load(image_url).into(ivPoster);
        } else ivPoster.setImageResource(R.drawable.movie_poster);
        tvTitle.setText(data.getStringExtra(extra_title));
        tvReleaseDate.setText(data.getStringExtra(extra_date));
        tvRating.setText(String.valueOf(data.getDoubleExtra(extra_rating, 0)));
        tvOverview.setText(data.getStringExtra(extra_overview));
    }


}