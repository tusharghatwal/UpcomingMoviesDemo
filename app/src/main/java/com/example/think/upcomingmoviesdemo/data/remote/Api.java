package com.example.think.upcomingmoviesdemo.data.remote;

import com.example.think.upcomingmoviesdemo.data.Images.MovieImages;
import com.example.think.upcomingmoviesdemo.data.configuration.Configuration;
import com.example.think.upcomingmoviesdemo.data.upcoming.Movies;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Think on 2/26/2018.
 */

public interface Api {
    String API_KEY = "b7cd3340a794e5a2f35e3abb820b497f";

    @GET("movie/upcoming")
    Observable<Movies> getMovies(@Query("api_key") String apikey);

    @GET("configuration")
    Observable<Configuration> getConfiguration(@Query("api_key") String apikey);

    @GET("movie/{movie_id}/images")
    public Observable<MovieImages> getImages(@Path("movie_id") String movieId, @Query("api_key") String apiKey);
}
