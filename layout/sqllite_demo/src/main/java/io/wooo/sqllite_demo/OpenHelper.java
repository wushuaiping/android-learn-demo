package io.wooo.sqllite_demo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 4;

    public OpenHelper(Context context) {
        super(context, "wooo.db", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table person(_id integer primary key autoincrement, name text, age integer)");
        System.out.println("------create table person successful------");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (VERSION == 3) {
            System.out.println("------add field address successful------");
            db.execSQL("alter table person add address text");
        }
    }
}
