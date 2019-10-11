package io.wooo.listview_demo3;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class OpenHelper extends SQLiteOpenHelper {

    private static final int VERSION = 2;

    public OpenHelper(Context context) {
        super(context, "person.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table person(_id integer primary key autoincrement, name text, age integer, sex text)");
        System.out.println("------create table person successful------");
        for (int i = 0; i < 19; i++) {
            Person person = new Person().getPerson();
            ContentValues values = new ContentValues();
            values.put("name", person.getName());
            values.put("age", person.getAge());
            values.put("sex", person.getSex());
            db.insert("person", null, values);
        }
        System.out.println("------create person successful------");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
