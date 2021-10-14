package com.spr.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.spr.moviedb.R;
import com.spr.moviedb.adapter.NowPlayingAdapter;
import com.spr.moviedb.model.NowPlaying;
import com.spr.moviedb.viewmodel.MovieVM;

public class NowPlayingActivity extends AppCompatActivity {

    private RecyclerView rv_np;
    private MovieVM vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
        vm = new ViewModelProvider(NowPlayingActivity.this).get(MovieVM.class);
        rv_np = findViewById(R.id.rv_np);
        vm.getNowPlaying();
        vm.getResultGetNowPlaying().observe(NowPlayingActivity.this, showNowPlaying);
    }

    private Observer<NowPlaying> showNowPlaying = new Observer<NowPlaying>() {
        @Override
        public void onChanged(NowPlaying nowPlaying) {
            rv_np.setLayoutManager(new LinearLayoutManager(NowPlayingActivity.this));
            NowPlayingAdapter adapter = new NowPlayingAdapter(NowPlayingActivity.this);
            adapter.setListNp(nowPlaying.getResults());
            rv_np.setAdapter(adapter);
        }
    };
}