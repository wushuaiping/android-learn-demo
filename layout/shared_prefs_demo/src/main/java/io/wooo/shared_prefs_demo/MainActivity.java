package io.wooo.shared_prefs_demo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickWrite(View view) {
        SharedPreferences sp = MainActivity.this.getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("username", "admin");
        edit.putString("password", "admin");
        edit.putInt("age", 24);
        edit.apply();
        Toast.makeText(this, "写入成功", Toast.LENGTH_SHORT).show();
    }

    public void onClickRead(View view) {
        SharedPreferences sp = MainActivity.this.getSharedPreferences("settings", MODE_PRIVATE);
        String username = sp.getString("username", "未找到username");
        String password = sp.getString("password", "未找到password");
        int age = sp.getInt("age", 18);
        Toast.makeText(this, "username：" + username + "，password：" + password + "，age" + age, Toast.LENGTH_SHORT).show();
    }

    public void onClickClear(View view) {
        SharedPreferences sp = MainActivity.this.getSharedPreferences("settings", MODE_PRIVATE);
        SharedPreferences.Editor clear = sp.edit().clear();
        clear.apply();
        Toast.makeText(this, "清空完成", Toast.LENGTH_SHORT).show();
    }

    public void xmlSave(View view) {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            books.add(new Book("吴承恩" + i, "西游记" + i));
        }
        try {
            File file = new File(MainActivity.this.getFilesDir(), "books.xml");
            OutputStream output = new FileOutputStream(file);
            XmlSerializer xmlSerializer = Xml.newSerializer();
            xmlSerializer.setOutput(output, "UTF-8");
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag(null, "books");
            for (Book book : books) {
                xmlSerializer.startTag(null, "book");
                xmlSerializer.startTag(null, "name");
                xmlSerializer.text(book.getName());
                xmlSerializer.endTag(null, "name");
                xmlSerializer.startTag(null, "author");
                xmlSerializer.text(book.getAuthor());
                xmlSerializer.endTag(null, "author");
                xmlSerializer.endTag(null, "book");
            }
            xmlSerializer.endTag(null, "books");
            xmlSerializer.endDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "xml保存完成", Toast.LENGTH_SHORT).show();
    }

    public void xmlRead(View view) {
        List<Book> books = new ArrayList<>();
        Book book = null;
        File file = new File(this.getFilesDir(), "books.xml");
        if (file.exists()) {
            try {
                InputStream inputStream = new FileInputStream(file);
                XmlPullParser parser = Xml.newPullParser();
                parser.setInput(inputStream, "UTF-8");
                int eventType = parser.getEventType();
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        if (parser.getName().equals("name")) {
                            book = new Book();
                            book.setName(parser.nextText());
                        }
                        if (parser.getName().equals("author")) {
                            book.setAuthor(parser.nextText());
                            books.add(book);
                        }
                    }
                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            StringBuilder toast = new StringBuilder();
            for (Book book1 : books) {
                toast.append(book1.getName()).append(", ").append(book1.getAuthor()).append(", ");
            }
            toast = new StringBuilder(toast.substring(0, toast.length() - 2));
            Toast.makeText(this, toast.toString(), Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "books.xml不存在", Toast.LENGTH_SHORT).show();
        }
    }

    public void xmlDelete(View view) {
        File file = new File(this.getFilesDir(), "books.xml");
        file.delete();
        Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show();
    }
}
