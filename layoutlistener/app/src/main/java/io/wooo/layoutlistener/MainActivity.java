package io.wooo.layoutlistener;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ok(View v) {
        Toast.makeText(this, "🐂🍺 + " + v.getId(), Toast.LENGTH_SHORT).show();
    }
}
