package com.spr.moviedb.retrofit;

import com.spr.moviedb.model.Movies;
import com.spr.moviedb.model.NowPlaying;
import com.spr.moviedb.model.Popular;
import com.spr.moviedb.model.Search;
import com.spr.moviedb.model.UpComing;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndPoint {

    @GET("movie/{movie_id}")
    Call<Movies> getMovieById(
            @Path("movie_id") String movieId,
            @Query("api_key") String apiKey
    );

    @GET("movie/now_playing")
    Call<NowPlaying> getNowPlaying(
            @Query("api_key") String apiKey

    );

    @GET("movie/upcoming")
    Call<UpComing> getUpComing(
            @Query("api_key") String apiKey
    );

    @GET("movie/popular")
    Call<Popular> getPopular(
            @Query("api_key") String apiKey
    );

    @GET("search/movie")
    Call<Search> getSearch(
            @Query("query") String query,
            @Query("api_key") String apiKey
    );
}
