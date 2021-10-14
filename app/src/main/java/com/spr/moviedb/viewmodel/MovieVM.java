package com.spr.moviedb.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.spr.moviedb.model.Movies;
import com.spr.moviedb.model.NowPlaying;
import com.spr.moviedb.repos.MovieRepo;

public class MovieVM extends AndroidViewModel {
    private MovieRepo repo;

    public MovieVM(@NonNull Application application) {
        super(application);
        repo = MovieRepo.getInstance();
    }

    private MutableLiveData<Movies> resultGetMovieById = new MutableLiveData<>();
    public void getMovieById(String id){
        resultGetMovieById = repo.getMovieData(id);
    }
    public LiveData<Movies> getResultGetMovieById(){
        return  resultGetMovieById;
    }


    private MutableLiveData<NowPlaying> resultGetNowPlaying = new MutableLiveData<>();
    public void getNowPlaying(){
        resultGetNowPlaying = repo.getNowPlayingData();
    }
    public LiveData<NowPlaying> getResultGetNowPlaying(){
        return  resultGetNowPlaying;
    }
}
