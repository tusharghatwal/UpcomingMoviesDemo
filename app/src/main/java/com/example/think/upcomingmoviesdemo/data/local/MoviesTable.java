package com.example.think.upcomingmoviesdemo.data.local;

import android.util.Log;

import com.example.think.upcomingmoviesdemo.data.model.Movie;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by Think on 06-Mar-18.
 */

public class MoviesTable {
    private DbHelper dbHelper;

    public MoviesTable(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void getallMovies() {
        ArrayList<Movie> movie = (ArrayList<Movie>) dbHelper.getDaoSession().getMovieDao().loadAll();
        for (Movie m : movie) {
            Log.e("db greendao = ", m.getOriginalTitle());
        }
    }

    public void insertOrReplace(Movie movie){
        dbHelper.getDaoSession().getMovieDao().insertOrReplace(movie);
    }

    public List<Movie> getOfflineMovies() {
        return dbHelper.getDaoSession().getMovieDao().loadAll();
    }

    public Observable<? extends List<Movie>> getLocalMovies(final Throwable throwable) {
        final List<Movie> movie = dbHelper.getDaoSession().getMovieDao().loadAll();

//        Observable<List<Movie>> movieObservable = Observable.just(movie);

        Observable<List<Movie>> movieObservable = Observable.create(new Observable.OnSubscribe<List<Movie>>() {
            @Override
            public void call(Subscriber<? super List<Movie>> subscriber) {
                if (!movie.isEmpty()){
                    subscriber.onNext(movie);
                    subscriber.onCompleted();
                }
                else {
                    subscriber.onError(throwable);
                }
            }
        });
        return movieObservable;
    }
}
