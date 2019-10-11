package io.wooo.listview_demo;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView view = findViewById(R.id.listView);
        view.setAdapter(new MyAdapter());
    }

    public class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 40;
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
        public View getView(int position, View oldView, ViewGroup parent) {
            TextView newView;
            if (oldView != null) {
                newView = (TextView) oldView;
                newView.setText("重复利用" + position);
            } else {
                newView = new TextView(MainActivity.this);
                newView.setText("文本" + position);
                newView.setTextSize(20);
            }
            System.out.println("index: " + position);
            return newView;
        }
    }
}
