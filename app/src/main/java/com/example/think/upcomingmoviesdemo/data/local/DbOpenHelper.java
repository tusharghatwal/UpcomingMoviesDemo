package com.example.think.upcomingmoviesdemo.data.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.think.upcomingmoviesdemo.data.model.DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Think on 05-Mar-18.
 */

public class DbOpenHelper extends DaoMaster.DevOpenHelper {
    private static final String DATABASE_NAME = "movies_app.db";

    public DbOpenHelper(Context context) {
        super(context, DATABASE_NAME);
    }

    public DbOpenHelper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, DATABASE_NAME, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        super.onUpgrade(db, oldVersion, newVersion);
    }
}
