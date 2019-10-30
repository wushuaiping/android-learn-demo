package io.wooo.more_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Hello2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello2);
        TextView name = findViewById(R.id.name);
        TextView birthday = findViewById(R.id.birthday);
        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        birthday.setText(intent.getStringExtra("birthday"));
    }
}
