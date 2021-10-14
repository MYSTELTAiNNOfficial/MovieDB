package com.spr.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.spr.moviedb.R;
import com.spr.moviedb.helper.Const;
import com.spr.moviedb.model.Movies;
import com.spr.moviedb.viewmodel.MovieVM;

public class MainActivity extends AppCompatActivity {

    private MovieVM vm;
    private Button button_hit_main;
    private TextInputLayout til_main;
    private TextView txt_show_main, txt_ori_main, txt_rd_main, txt_desc_main;
    private ImageView cover_main;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vm = new ViewModelProvider(MainActivity.this).get(MovieVM.class);
        button_hit_main = findViewById(R.id.button_hit_main);
        txt_show_main = findViewById(R.id.txt_show_main);
        txt_ori_main = findViewById(R.id.txt_genre_md);
        txt_rd_main = findViewById(R.id.txt_rd_main);
        txt_desc_main = findViewById(R.id.txt_desc_main);
        til_main = findViewById(R.id.til_main);
        cover_main = findViewById(R.id.cover_main);
        Glide.with(MainActivity.this).clear(cover_main);
        txt_rd_main.setText(null);
        txt_ori_main.setText(null);
        txt_desc_main.setText(null);
        txt_show_main.setText(null);
        txt_desc_main.setMovementMethod(new ScrollingMovementMethod());
        button_hit_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String searchId = til_main.getEditText().getText().toString().trim();
                if (searchId.isEmpty()){
                    til_main.setError("Please fill the movie ID");
                }else{
                    til_main.setError(null);
                    vm.getMovieById(searchId);
                    vm.getResultGetMovieById().observe(MainActivity.this, showResultMovie);
                }

            }
        });
    }

    private Observer<Movies> showResultMovie = new Observer<Movies>() {
        @Override
        public void onChanged(Movies movies) {
            if (movies == null){
                txt_show_main.setText("ID is not available in TMDB");
                Glide.with(MainActivity.this).clear(cover_main);
                txt_rd_main.setText(null);
                txt_ori_main.setText(null);
                txt_desc_main.setText(null);
            }else{
                String title =  movies.getTitle();
                String desc = movies.getOverview();
                String ori = movies.getOriginal_title();
                String rd = movies.getRelease_date();
                String img_path = movies.getPoster_path().toString();
                String full_path = Const.COVER_URL + img_path;
                txt_show_main.setText(title);
                txt_rd_main.setText(rd);
                txt_ori_main.setText(ori);
                txt_desc_main.setText(desc);
                Glide.with(MainActivity.this).load(full_path).into(cover_main);
            }
        }
    };
}