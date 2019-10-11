package io.wooo.adapter_demo;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.listView);
        List<String> list = new ArrayList<>();
        list.add("猴子");
        list.add("大象");
        list.add("蛇");
        list.add("马");
        list.add("猪");
        list.add("鱼");
        list.add("龙");
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item1, list);
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.item2, R.id.textView3, list);
        List<Map<String, Object>> data = new ArrayList<>();
        Map<String, Object> map1 = new HashMap<>();
        map1.put("name", "张三");
        map1.put("age", "20");
        data.add(map1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("name", "李四");
        map2.put("age", "30");
        data.add(map2);
        Map<String, Object> map3 = new HashMap<>();
        map3.put("name", "王五");
        map3.put("age", "22");
        data.add(map3);
        Map<String, Object> map4 = new HashMap<>();
        map4.put("name", "王二麻子");
        map4.put("age", "27");
        data.add(map4);
        SimpleAdapter adapter = new SimpleAdapter(this, data, R.layout.item3, new String[]{"name", "age"}, new int[]{R.id.lv_name, R.id.lv_age});
        listView.setAdapter(adapter);
    }
}
