package io.wooo.http_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    hello();
                } catch (IOException e) {

                }
            }
        }).start();
    }

    public void hello() throws IOException {
        URL url = new URL("http://localhost:9999/index.html");
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();

        try (InputStream inputStream = connection.getInputStream()){
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(3000);
            connection.setReadTimeout(3000);
            connection.connect();
            int responseCode = connection.getResponseCode();
            StringBuffer sb = new StringBuffer();

            if (responseCode == 200){

                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String readLine;
                while ((readLine = bufferedReader.readLine()) != null){
                    sb.append(readLine);
                }
            }
            System.out.println(sb);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
