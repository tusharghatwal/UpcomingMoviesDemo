package com.example.think.upcomingmoviesdemo.data.local;

import android.util.Log;

import com.example.think.upcomingmoviesdemo.data.model.Movie;
import com.example.think.upcomingmoviesdemo.data.model.MovieDao;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Created by Think on 06-Mar-18.
 */

public class MoviesTable {
    private static MovieDao movieDao = DbHelper.getInstance().getDaoSession().getMovieDao();

    public void getAllMovies() {
        ArrayList<Movie> movie = (ArrayList<Movie>) movieDao.loadAll();
        for (Movie m : movie) {
            Log.e("db greendao = ", m.getOriginalTitle());
        }
    }

    public static void insertOrReplace(Movie movie){
        movieDao.insertOrReplace(movie);
    }

    public List<Movie> getOfflineMovies() {
        return movieDao.loadAll();
    }

    public static Observable<? extends List<Movie>> getLocalMovies() {
        final List<Movie> movie = movieDao.loadAll();

//        Observable<List<Movie>> movieObservable = Observable.just(movie);

        /*Observable<List<Movie>> movieObservable = Observable.create(new Observable.OnSubscribe<List<Movie>>() {
            @Override
            public void call(Subscriber<? super List<Movie>> subscriber) {
                if (!movie.isEmpty()){
                    subscriber.onNext(movie);
                    subscriber.onCompleted();
                }
                else {
                    subscriber.onError();
                }
            }
        });*/
        return Observable.just(movie);
    }
}
