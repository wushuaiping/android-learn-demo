package io.wooo.more_activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void callPhone(View view){
        // 创建意图
        Intent intent = new Intent();

        // 检查是否有拨打电话权限。
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED){
            // 拨打电话
            intent.setAction(Intent.ACTION_CALL);
            // 需要拨打的电话号码
            intent.setData(Uri.parse("tel:110"));
        }else {
            // 没有权限则申请
            requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1);
            // 拨打电话
            intent.setAction(Intent.ACTION_CALL);
            // 需要拨打的电话号码
            intent.setData(Uri.parse("tel:110"));
        }
        startActivity(intent);
    }

    public void clickOther(View view){
        Intent intent = new Intent();
        intent.setAction("io.wooo.other.action");
        Uri parse = Uri.parse("wooo: hahah");
//        intent.setData(parse);
//        intent.setType("wooo/qwe");
        intent.setDataAndType(parse, "wooo/qwe");
        startActivity(intent);
    }

    public void clickOther2(View view){
        Intent intent = new Intent();
//        intent.setClassName("io.wooo.more_activity", "io.wooo.more_activity.HelloActivity");
        intent.setClass(this, HelloActivity.class);
        startActivity(intent);
    }

    public void clickOther3(View view){
        Intent intent = new Intent();
        intent.setClass(this, Hello2Activity.class);
        intent.putExtra("name", "刘雨佳");
        intent.putExtra("birthday", "1994-06-01");
        startActivity(intent);
    }

    public void clickOther4(View view){
        Intent intent = new Intent();
        intent.setClass(this, Hello3Activity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        assert data != null;
        Toast.makeText(this, data.getStringExtra("love"), Toast.LENGTH_SHORT).show();
    }
}
