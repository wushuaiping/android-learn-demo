package io.wooo.more_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Hello3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hello3);
    }

    public void goBackHome(View view) {
        Intent intent = new Intent();
        intent.putExtra("love", "I will never give up love liu yu jia ");
        setResult(1, intent);
        finish();
    }
}
