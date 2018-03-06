package com.example.think.upcomingmoviesdemo.data.local;

import android.content.Context;

import com.example.think.upcomingmoviesdemo.data.model.DaoMaster;
import com.example.think.upcomingmoviesdemo.data.model.DaoSession;
import com.example.think.upcomingmoviesdemo.data.model.Movie;

import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.rx.RxDao;

/**
 * Created by Think on 05-Mar-18.
 */

public class DbHelper {

    private static final String DATABASE_NAME = "movies_app.db";

    private DaoSession daoSession;
    private static DbHelper dbHelper;

    private DbHelper(Context context) {
        DbOpenHelper helper = new DbOpenHelper(context, DATABASE_NAME);
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public static void init(Context context) {
        if(dbHelper == null) {
            dbHelper = new DbHelper(context);
        }
    }

    public static DbHelper getInstance(){
        if(dbHelper == null) throw new IllegalStateException("DbHelper Instance is null");

        return  dbHelper;
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }

    public RxDao<Movie, String> getMovieDao(){
        return daoSession.getMovieDao().rx();
    }
}
