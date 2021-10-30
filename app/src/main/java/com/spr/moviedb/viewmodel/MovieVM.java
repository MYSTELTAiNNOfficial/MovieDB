package com.spr.moviedb.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.spr.moviedb.model.Movies;
import com.spr.moviedb.model.NowPlaying;
import com.spr.moviedb.model.Popular;
import com.spr.moviedb.model.Search;
import com.spr.moviedb.model.UpComing;
import com.spr.moviedb.repos.MovieRepo;

public class MovieVM extends AndroidViewModel {
    private MovieRepo repo;

    public MovieVM(@NonNull Application application) {
        super(application);
        repo = MovieRepo.getInstance();
    }

    private MutableLiveData<Movies> resultGetMovieById = new MutableLiveData<>();

    public void getMovieById(String id) {
        resultGetMovieById = repo.getMovieData(id);
    }

    public LiveData<Movies> getResultGetMovieById() {
        return resultGetMovieById;
    }


    private MutableLiveData<NowPlaying> resultGetNowPlaying = new MutableLiveData<>();

    public void getNowPlaying() {
        resultGetNowPlaying = repo.getNowPlayingData();
    }

    public LiveData<NowPlaying> getResultGetNowPlaying() {
        return resultGetNowPlaying;
    }


    private MutableLiveData<UpComing> resultGetUpComing = new MutableLiveData<>();

    public void getUpComing() {
        resultGetUpComing = repo.getUpComingData();
    }

    public LiveData<UpComing> getResultGetUpComing() {
        return resultGetUpComing;
    }


    private MutableLiveData<Popular> resultGetPopular = new MutableLiveData<>();

    public void getPopular() {
        resultGetPopular = repo.getPopularData();
    }

    public LiveData<Popular> getResultGetPopular() {
        return resultGetPopular;
    }


    private MutableLiveData<Search> resultGetSearch = new MutableLiveData<>();

    public void getSearch(String query) {
        resultGetSearch = repo.getSearchData(query);
    }

    public LiveData<Search> getResultGetSearch() {
        return resultGetSearch;
    }
}
