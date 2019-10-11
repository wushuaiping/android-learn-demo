package io.wooo.sqllite_demo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void crud(View view) {
        OpenHelper openHelper = new OpenHelper(this);
        SQLiteDatabase database = openHelper.getWritableDatabase();
        switch (view.getId()) {
            case R.id.button:
//                database.execSQL("insert into person(name, age) values(?, ?)", new Object[]{"李四", 24});
                ContentValues values = new ContentValues();
                values.put("name", "王二麻子");
                values.put("age", 99);
                database.insert("person", null, values);
                break;
            case R.id.button2:
//                database.execSQL("update person set age = 18 where name = ?", new String[]{"李四"});
                values = new ContentValues();
                values.put("age", 33);
                database.update("person", values, "name=?", new String[]{"王二麻子"});
                break;
            case R.id.button3:
                Cursor cursor = database.query("person", null, null, null, null, null, null);
//                Cursor cursor = database.rawQuery("select * from person", null);
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(0);
                    String name = cursor.getString(1);
                    int age = cursor.getInt(2);
                    System.out.println(String.format("id=%s, name=%s, age=%s", id, name, age));
                }
                break;
            case R.id.button4:
                database.delete("person", "name=?", new String[]{"王二麻子"});
//                database.execSQL("delete from person where name = ?", new String[]{"李四"});
                break;
        }
        database.close();
    }

    public void transaction(View view) {
        OpenHelper openHelper = new OpenHelper(this);
        SQLiteDatabase database = openHelper.getWritableDatabase();
        try {
            database.beginTransaction();
            for (int i = 0; i < 9; i++) {
                ContentValues values = new ContentValues();
                values.put("name", "测试事物二" + i);
                values.put("age", 10 + i);
                database.insert("person", null, values);
            }
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }
}
