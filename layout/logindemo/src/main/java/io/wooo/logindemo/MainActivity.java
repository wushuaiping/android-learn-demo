package io.wooo.logindemo;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText username;

    private EditText password;

    private CheckBox remPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        remPassword = findViewById(R.id.cb_remPassword);
        loadInfo();
    }

    /**
     * 读取用户记住的密码
     */
    private void loadInfo() {
        try (FileInputStream fileInputStream = MainActivity.this.openFileInput("user.data")) {
            byte[] buffer = new byte[512];
            int len = fileInputStream.read(buffer);
            String[] user = new String(buffer, 0, len).split("#");
            username.setText(user[0]);
            password.setText(user[1]);
            remPassword.setChecked(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 登录
     *
     * @param view
     */
    public void login(View view) {
        Context context = MainActivity.this;

        // 获取SD卡情况
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            Toast.makeText(context, "SD卡已就绪", Toast.LENGTH_SHORT).show();
            File sdcardFile = Environment.getExternalStorageDirectory();
            long usableSpace = sdcardFile.getUsableSpace();
            long totalSpace = sdcardFile.getTotalSpace();
            String usable = Formatter.formatFileSize(context, usableSpace);
            String total = Formatter.formatFileSize(context, totalSpace);
            Toast.makeText(context, "SD卡剩余容量：" + usable + "/" + "SD卡总容量：" + total, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "SD卡不存在或者未就绪", Toast.LENGTH_SHORT).show();
        }

        if (TextUtils.isEmpty(username.getText()) || TextUtils.isEmpty(password.getText())) {
            Toast.makeText(context, "请输入正确的用户名或密码", Toast.LENGTH_SHORT).show();
        } else {

            // 记住密码
            if (remPassword.isChecked()) {
                try (OutputStream writer = context.openFileOutput("user.data", MODE_PRIVATE)) {
                    String user = username.getText() + "#" + password.getText();
                    writer.write(user.getBytes(StandardCharsets.UTF_8));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        Toast.makeText(context, "登陆成功", Toast.LENGTH_SHORT).show();
    }
}
