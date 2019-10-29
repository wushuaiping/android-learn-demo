package io.wooo.http_img_demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;

//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            Bitmap bitmap = (Bitmap) msg.obj;
//            imageView.setImageBitmap(bitmap);
//        }
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void hello() {
        EditText editText = findViewById(R.id.inputUrl);
        String s = editText.getText().toString();
        URL url;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        File file = new File(getCacheDir(), Base64.encodeToString(s.getBytes(), Base64.DEFAULT));
        if (!file.exists()){
            try {
                url = new URL(s);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(3000);
                connection.setReadTimeout(3000);
                connection.connect();
                inputStream = connection.getInputStream();
                outputStream = new FileOutputStream(file);
                int len;
                byte[] buffer = new byte[1024];
                while ((len = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
                System.out.println("使用的网络请求");
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    if (outputStream != null) {
                        outputStream.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        final Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        runOnUiThread(() -> imageView.setImageBitmap(bitmap));
//        Message message = Message.obtain();
//        message.obj = bitmap;
//        handler.sendMessage(message);
    }

    public void read(View view) {
        imageView = findViewById(R.id.imageView);
        new Thread(new Runnable() {
            @Override
            public void run() {
                hello();
            }
        }).start();
    }
}
