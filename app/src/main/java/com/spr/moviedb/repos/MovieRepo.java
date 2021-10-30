package com.spr.moviedb.repos;

import androidx.lifecycle.MutableLiveData;

import com.spr.moviedb.helper.Const;
import com.spr.moviedb.model.Movies;
import com.spr.moviedb.model.NowPlaying;
import com.spr.moviedb.model.Popular;
import com.spr.moviedb.model.Search;
import com.spr.moviedb.model.UpComing;
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

    public MutableLiveData<UpComing> getUpComingData(){
        final MutableLiveData<UpComing> result = new MutableLiveData<>();

        ApiService.endPoint().getUpComing(Const.API_KEY).enqueue(new Callback<UpComing>() {
            @Override
            public void onResponse(Call<UpComing> call, Response<UpComing> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UpComing> call, Throwable t) {

            }
        });
        return result;
    }

    public MutableLiveData<Popular> getPopularData(){
        final MutableLiveData<Popular> result = new MutableLiveData<>();

        ApiService.endPoint().getPopular(Const.API_KEY).enqueue(new Callback<Popular>() {
            @Override
            public void onResponse(Call<Popular> call, Response<Popular> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Popular> call, Throwable t) {

            }
        });
        return result;
    }

    public MutableLiveData<Search> getSearchData(String query){
        final MutableLiveData<Search> result = new MutableLiveData<>();

        ApiService.endPoint().getSearch(query,Const.API_KEY).enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                result.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {

            }
        });
        return result;
    }
}
