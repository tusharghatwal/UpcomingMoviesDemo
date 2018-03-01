package com.example.think.upcomingmoviesdemo.ui.main;

import com.example.think.upcomingmoviesdemo.data.configuration.Configuration;
import com.example.think.upcomingmoviesdemo.data.local.PreferencesHelper;
import com.example.think.upcomingmoviesdemo.data.model.Movie;
import com.example.think.upcomingmoviesdemo.data.remote.Api;
import com.example.think.upcomingmoviesdemo.data.upcoming.Movies;
import com.example.think.upcomingmoviesdemo.data.remote.RetrofitFactory;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by Think on 2/26/2018.
 */

class MainActivityPresenter {

    private MainActivityView mView;
    private Subscription mSubscription;
    private final PreferencesHelper mPreferenceHelper;

    MainActivityPresenter(MainActivityView view, PreferencesHelper mPreferenceHelper) {
        this.mView = view;
        this.mPreferenceHelper = mPreferenceHelper;
    }

    void getConfigurationAndLoadMovies() {
        mSubscription = Observable.combineLatest(RetrofitFactory.getRetrofitApi().getMovies(Api.API_KEY),
                RetrofitFactory.getRetrofitApi().getConfiguration(Api.API_KEY),
                new Func2<Movies, Configuration, List<Movie>>() {
                    @Override
                    public List<Movie> call(Movies movies, Configuration configuration) {
                        configuration.save(mPreferenceHelper);
                        return movies.getResults();
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
