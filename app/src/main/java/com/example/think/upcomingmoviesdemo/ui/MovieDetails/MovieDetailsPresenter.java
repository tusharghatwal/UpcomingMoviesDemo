package com.example.think.upcomingmoviesdemo.ui.MovieDetails;

import com.example.think.upcomingmoviesdemo.data.Images.Backdrop;
import com.example.think.upcomingmoviesdemo.data.Images.MovieImages;
import com.example.think.upcomingmoviesdemo.data.remote.Api;
import com.example.think.upcomingmoviesdemo.data.remote.RetrofitFactory;

import java.util.ArrayList;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Think on 27-Feb-18.
 */

class MovieDetailsPresenter {

    private MovieDetailsView mView;
    private Subscription mSubscription;
    private ArrayList<Backdrop> mListBackdrops = new ArrayList<>();

    MovieDetailsPresenter(MovieDetailsView mView) {
        this.mView = mView;
    }

    void getMovieImages(String id) {

        mSubscription = RetrofitFactory.getRetrofitApi().getImages(id, Api.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieImages>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(MovieImages movieImages) {
                        mListBackdrops.addAll(movieImages.getBackdrops());
                        mView.setViewPager(mListBackdrops);
                    }
                });
    }

    void unSubscribeSubscription() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) mSubscription.unsubscribe();
    }
}
