package io.wooo.http_demo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private URL url;

    private TextView textView;

    private final static int SUCCESS = 1;

    private final static int ERROR = 2;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            if (msg.what == SUCCESS) {
                String text = (String) msg.obj;
                textView.setText(text);
            } else {
                textView.setText("");
                Toast.makeText(MainActivity.this, "无法获取资源", Toast.LENGTH_SHORT).show();
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView2);
    }

    public void read(View view) {
        try {
            EditText editText = findViewById(R.id.inputUrl);
            url = new URL(editText.getText().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    hello();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void hello() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        try (InputStream inputStream = connection.getInputStream()) {
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            connection.connect();
            int responseCode = connection.getResponseCode();
            StringBuffer sb = new StringBuffer();
            if (responseCode == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String readLine;
                while ((readLine = bufferedReader.readLine()) != null) {
                    sb.append(readLine);
                }
                Message msg = new Message();
                msg.obj = sb.toString();
                msg.what = SUCCESS;
                handler.sendMessage(msg);
            } else {
                Message msg = new Message();
                msg.what = ERROR;
                handler.sendMessage(msg);
            }
            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
