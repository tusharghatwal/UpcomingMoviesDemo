package com.example.think.upcomingmoviesdemo.data;

import com.example.think.upcomingmoviesdemo.data.configuration.Configuration;
import com.example.think.upcomingmoviesdemo.data.local.MoviesTable;
import com.example.think.upcomingmoviesdemo.data.local.PreferencesHelper;
import com.example.think.upcomingmoviesdemo.data.model.Movie;
import com.example.think.upcomingmoviesdemo.data.remote.Api;
import com.example.think.upcomingmoviesdemo.data.remote.RetrofitFactory;
import com.example.think.upcomingmoviesdemo.data.upcoming.Movies;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Think on 06-Mar-18.
 */

public class DataManager {
    //private final Api api;
    private final PreferencesHelper preferencesHelper;

    public DataManager(PreferencesHelper preferencesHelper) {
        //this.api = api;
        this.preferencesHelper = preferencesHelper;
    }

    public Observable<Configuration> getConfiguration() {
        return RetrofitFactory.getRetrofitApi().getConfiguration(Api.API_KEY)
                .doOnNext(new Action1<Configuration>() {
                    @Override
                    public void call(Configuration configuration) {
                        configuration.save(preferencesHelper);
                    }
                })
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends Configuration>>() {
                    @Override
                    public Observable<? extends Configuration> call(Throwable throwable) {
                        Configuration configuration = new Configuration();
                        return Observable.just(configuration);
                    }
                });
    }

    public Observable<Movies> getMovies() {
        return RetrofitFactory.getRetrofitApi().getMovies(Api.API_KEY)
                .doOnNext(new Action1<Movies>() {
                    @Override
                    public void call(Movies movies) {
                        for (Movie movie : movies.getResults()) {
                            MoviesTable.insertOrReplace(movie);
                        }
                    }
                });
    }
}
