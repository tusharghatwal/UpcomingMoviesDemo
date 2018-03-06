package com.example.think.upcomingmoviesdemo.ui.main;

import com.example.think.upcomingmoviesdemo.data.configuration.Configuration;
import com.example.think.upcomingmoviesdemo.data.local.MoviesTable;
import com.example.think.upcomingmoviesdemo.data.local.PreferencesHelper;
import com.example.think.upcomingmoviesdemo.data.model.Movie;
import com.example.think.upcomingmoviesdemo.data.remote.Api;
import com.example.think.upcomingmoviesdemo.data.remote.RetrofitFactory;
import com.example.think.upcomingmoviesdemo.data.upcoming.Movies;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by Think on 2/26/2018.
 */

class MainActivityPresenter {

    private MainActivityView mView;
    private Subscription mSubscription;
    private final PreferencesHelper mPreferenceHelper;
    private final MoviesTable moviesTable;

    MainActivityPresenter(MainActivityView view, PreferencesHelper mPreferenceHelper, MoviesTable moviesTable) {
        this.mView = view;
        this.mPreferenceHelper = mPreferenceHelper;
        this.moviesTable = moviesTable;
    }

    void getConfigurationAndLoadMovies() {
        mSubscription = Observable.combineLatest(RetrofitFactory.getRetrofitApi().getMovies(Api.API_KEY),
                RetrofitFactory.getRetrofitApi().getConfiguration(Api.API_KEY),
                new Func2<Movies, Configuration, List<Movie>>() {
                    @Override
                    public List<Movie> call(Movies movies, Configuration configuration) {
                        configuration.save(mPreferenceHelper);
                        for (Movie m: movies.getResults()) {
                            moviesTable.insertOrReplace(m);
                        }
                        return movies.getResults();
                    }

                })
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends List<Movie>>>() {
                    @Override
                    public Observable<? extends List<Movie>> call(Throwable throwable) {
                        return moviesTable.getLocalMovies(throwable);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Movie>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<Movie> movie) {
                        mView.setActivityView(movie);
                    }
                });
    }

    void unSubscribeSubscription() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) mSubscription.unsubscribe();
    }
}
