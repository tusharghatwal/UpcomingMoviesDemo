package com.example.think.upcomingmoviesdemo;

import android.app.Application;

import com.example.think.upcomingmoviesdemo.data.local.DbHelper;

/**
 * Created by Think on 06-Mar-18.
 */

public class UpcomingMoviesDemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        DbHelper.init(this);
    }
}
