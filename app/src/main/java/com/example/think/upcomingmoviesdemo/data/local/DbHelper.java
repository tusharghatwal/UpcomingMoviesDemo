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

    private static Context mContext;
//    private static DbHelper dbHelper;

    private DaoSession daoSession;

    private DbHelper() {
        DbOpenHelper helper = new DbOpenHelper(mContext);
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public static DbHelper getInstance(Context context){
        mContext = context;
        return new DbHelper();
    }

    public DaoSession getDaoSession(){
        return daoSession;
    }

    public RxDao<Movie, String> getMovieDao(){
        return daoSession.getMovieDao().rx();
    }
}
