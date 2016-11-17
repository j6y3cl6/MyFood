package com.example.wuzihao.myfood;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by wuzihao on 2016/11/16.
 */

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "food.db";
    private static final int DB_VERSION = 1;
    private final static String creatTable =
            "CREATE TABLE foodMenu(name TEXT, tag TEXY,addr TEXT)";

    public MyDBHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(creatTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
