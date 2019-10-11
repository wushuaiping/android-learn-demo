package io.wooo.telephonedialer;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Activity mainActivity = this;
        setContentView(R.layout.activity_main);
        // 获取输入框
        final EditText editText = findViewById(R.id.editText);
        // 获取按钮
        Button button = findViewById(R.id.button);
        button.setOnClickListener((view) -> {
            String phone = editText.getText().toString();
            if (phone == null || phone.isEmpty()) {
                Toast.makeText(MainActivity.this, "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                return;
            }
            // 请求获取动态权限
            ActivityCompat.requestPermissions(mainActivity, new String[]{Manifest.permission.CALL_PHONE}, 0x11);

            // 创建意图
            Intent intent = new Intent();
            // 设置动作 打电话
            intent.setAction(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + phone));
            startActivity(intent);
        });
    }
}
