package io.wooo.news_demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.image.SmartImageView;
import com.zhouyou.http.EasyHttp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Result result = null;
    private ListView listView;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listView);
        EasyHttp.init(getApplication());
        getNews();
    }

    private class MyListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return Objects.nonNull(result) ? result.getNews().size() : 0;
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
            View view;
            if (convertView == null) {
                view = View.inflate(getApplicationContext(), R.layout.item, null);
            } else {
                view = convertView;
            }
            if (Objects.nonNull(result) && Objects.nonNull(result.getNews())) {
                List<News> newsList = result.getNews();
                News news = newsList.get(position);
                TextView source = view.findViewById(R.id.tv_source);
                TextView commentCount = view.findViewById(R.id.tv_commentCount);
                TextView titleView = view.findViewById(R.id.tv_title);
                TextView newsDesc = view.findViewById(R.id.tv_news_desc);
                SmartImageView image = view.findViewById(R.id.iv_logo);
                image.setImageUrl(news.getImgsrc());
                commentCount.setText(String.valueOf(news.getCommentCount()));
                source.setText(news.getSource());
                titleView.setText(news.getTitle());
                newsDesc.setText(news.getDigest());
            }
            return view;
        }
    }

    public void clickTitle(View view) {
        int[] ids = {R.id.tv_all, R.id.tv_tv, R.id.tv_movie, R.id.tv_star, R.id.tv_happy};
        for (int id : ids) {
            TextView textView = findViewById(id);
            textView.setTextColor(-1979711488);
        }
        TextView textView = (TextView) view;
        textView.setTextColor(Color.parseColor("#F44336"));
        getNews();
    }

    public void getNews() {
        String newsUrl = "http://10.0.2.2:9999/index/news";
        AtomicReference<Result> news = new AtomicReference<>();
        new Thread(() -> {
            OkHttpClient client = new OkHttpClient();
            final Request request = new Request.Builder().get().url(newsUrl).build();
            Call call = client.newCall(request);
            try {
                Response execute = call.execute();
                String body = execute.body().string();
                Result result = new Gson().fromJson(body, Result.class);
                news.set(result);
                setResult(result);
                runOnUiThread(() -> listView.setAdapter(new MyListAdapter()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void loadBitmap(final String imgUrl, ImageView imageView) {
        new Thread(() -> {
            URL url;
            InputStream inputStream = null;
            OutputStream outputStream = null;
            File file = new File(getCacheDir(), Base64.encodeToString(imgUrl.getBytes(), Base64.DEFAULT));
            if (!file.exists()) {
                try {
                    url = new URL(imgUrl);
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

            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            runOnUiThread(() -> imageView.setImageBitmap(bitmap));
        }).start();

    }

}
