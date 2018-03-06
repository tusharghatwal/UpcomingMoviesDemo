package com.example.think.upcomingmoviesdemo.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.think.upcomingmoviesdemo.data.model.DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Think on 05-Mar-18.
 */

public class DbOpenHelper extends DaoMaster.DevOpenHelper {
    public DbOpenHelper(Context context, String dbname) {
        super(context, dbname);
    }

    public DbOpenHelper(Context context, String dbname, SQLiteDatabase.CursorFactory factory) {
        super(context, dbname, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }
}
