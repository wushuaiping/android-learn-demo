package io.wooo.listview_demo3;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private List<Person> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OpenHelper openHelper = new OpenHelper(this);
        SQLiteDatabase database = openHelper.getWritableDatabase();
        Cursor cursor = database.rawQuery("select * from person", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            int age = cursor.getInt(2);
            String sex = cursor.getString(3);
            System.out.println(String.format("id=%s, name=%s, age=%s, sex=%s", id, name, age, sex));
            Person person = new Person();
            person.setAge(age);
            person.setSex(sex);
            person.setName(name);
            list.add(person);
        }
        ListView listView = findViewById(R.id.lv);
        listView.setAdapter(new MyListAdapter());
    }

    private class MyListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Person person = list.get(position);
            View view;
            if (convertView == null) {
                view = View.inflate(getApplicationContext(), R.layout.item, null);
            } else {
                view = convertView;
            }
            TextView age = view.findViewById(R.id.tv_age);
            age.setText(String.valueOf(person.getAge()));
            TextView name = view.findViewById(R.id.tv_name);
            name.setText(person.getName());
            TextView sex = view.findViewById(R.id.tv_sex);
            sex.setText(person.getSex());
            return view;
        }
    }
}
