package com.ibrahim.trendingmovies;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private Context context;
    private List<Movie> movies;
    private OnMovieListener listener;

    public MovieAdapter(List<Movie> moviesList , Context context) {
        this.movies = moviesList;
        this.context = context;
    }

    @NonNull
    @NotNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_item, parent, false);

        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MovieAdapter.MovieViewHolder holder, int position) {
        Movie currentMovie = movies.get(position);
        Uri image_url = Uri.parse(currentMovie.getImagePath());

        Glide.with(context).load(image_url).into(holder.ivMoviePoster);
        holder.tvMovieTitle.setText(currentMovie.getName());
        holder.tvMovieDate.setText(currentMovie.getDate());
        holder.tvMovieRate.setText(String.valueOf(currentMovie.getRating()));
    }

    @Override
    public int getItemCount() {
        //movies == null ? 0 :
        return movies.size();
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
        //not included
        //notifyDataSetChanged();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivMoviePoster;
        private TextView tvMovieTitle;
        private TextView tvMovieDate;
        private TextView tvMovieRate;

        public MovieViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            ivMoviePoster = itemView.findViewById(R.id.iv_movie_poster);
            tvMovieTitle = itemView.findViewById(R.id.tv_movie_title);
            tvMovieDate = itemView.findViewById(R.id.tv_movie_date);
            tvMovieRate = itemView.findViewById(R.id.tv_movie_rating);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onMovieClick(movies.get(position));
                    }
                }
            });
        }
    }

    public interface OnMovieListener {
        void onMovieClick(Movie movieItems);
    }

    public void setOnItemClickLister(OnMovieListener listener) {
        this.listener = listener;

    }
}
