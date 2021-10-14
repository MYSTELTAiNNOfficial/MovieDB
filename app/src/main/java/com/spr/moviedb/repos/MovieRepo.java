package com.spr.moviedb.repos;

import androidx.lifecycle.MutableLiveData;

import com.spr.moviedb.helper.Const;
import com.spr.moviedb.model.Movies;
import com.spr.moviedb.model.NowPlaying;
import com.spr.moviedb.retrofit.ApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepo {

    private static MovieRepo repo;

    private MovieRepo() {
    }

    public static MovieRepo getInstance() {
        if (repo == null) {
            repo = new MovieRepo();
        }
        return repo;
    }

    public MutableLiveData<Movies> getMovieData(String id) {
        final MutableLiveData<Movies> result = new MutableLiveData<>();

        ApiService.endPoint().getMovieById(id, Const.API_KEY).enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {

            }
        });

        return result;
    }

    public MutableLiveData<NowPlaying> getNowPlayingData() {
        final MutableLiveData<NowPlaying> result = new MutableLiveData<>();

        ApiService.endPoint().getNowPlaying(Const.API_KEY).enqueue(new Callback<NowPlaying>() {

            @Override
            public void onResponse(Call<NowPlaying> call, Response<NowPlaying> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<NowPlaying> call, Throwable t) {

            }
        });
        return result;
    }
}
