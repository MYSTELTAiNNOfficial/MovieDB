package com.spr.moviedb.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.spr.moviedb.R;
import com.spr.moviedb.helper.Const;
import com.spr.moviedb.model.Movies;
import com.spr.moviedb.viewmodel.MovieVM;


public class MovieDetailsActivity extends AppCompatActivity {

    private TextView txt_show_md,txt_genre_md,txt_rate_md,txt_desc_md,txt_rd_md,txt_status_md;
    private ImageView cover_md;
    private String movie_id ="";
    private MovieVM vm;


    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        txt_show_md = findViewById(R.id.txt_show_md);
        txt_genre_md = findViewById(R.id.txt_genre_md);
        txt_rate_md = findViewById(R.id.txt_rate_md);
        txt_desc_md = findViewById(R.id.txt_desc_md);
        txt_rd_md = findViewById(R.id.txt_rd_md);
        txt_status_md = findViewById(R.id.txt_status_md);
        cover_md = findViewById(R.id.cover_md);
        Intent intent = getIntent();
        movie_id = intent.getStringExtra("movie_id");
        vm= new ViewModelProvider(MovieDetailsActivity.this).get(MovieVM.class);
        vm.getMovieById(movie_id);
        vm.getResultGetMovieById().observe(MovieDetailsActivity.this, showResultMovie);
    }

    private Observer<Movies> showResultMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            String title =  movies.getTitle();
            String genres ="";
            String desc = movies.getOverview();
            String rate = String.valueOf(movies.getVote_average());
            String rd = movies.getRelease_date();
            String status = movies.getStatus();
            String img_path = movies.getPoster_path().toString();
            String full_path = Const.IMAGE_URL + img_path;
            for (int i = 0; i<movies.getGenres().size(); i++){
                if (i==movies.getGenres().size()-1) { // i = 1, genre size = 2-1 = 1
                    genres += movies.getGenres().get(i).getName();
                }else{
                    genres += movies.getGenres().get(i).getName()+", ";
                }
            }
            txt_show_md.setText(title);
            txt_rate_md.setText("Rate: "+rate);
            txt_desc_md.setText(desc);
            txt_rd_md.setText("Release Date: "+rd);
            txt_status_md.setText("Status: "+status);
            txt_genre_md.setText("Genre: "+genres);
            Glide.with(MovieDetailsActivity.this).load(full_path).into(cover_md);
        }
    };
}