package io.wooo.activitydemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class FirstActivity extends BaseActivity {

    /**
     * 创建应用
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Log.d(FirstActivity.class.getCanonicalName(), "FirstActivity taskId:" + getTaskId());
        setContentView(R.layout.first_layout);
        View button1 = findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SecondActivity.actionStart(FirstActivity.this, "data1", "data2");
            }
        });
    }

    /**
     * 定义菜单
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_item:
                Toast.makeText(this, "点击了新增", Toast.LENGTH_SHORT).show();
                break;
            case R.id.remove_item:
                Toast.makeText(this, "点击了删除", Toast.LENGTH_SHORT).show();
            default:
                return true;
        }
        return true;
    }

    /**
     * 重写onActivityResult方法 以获得返回数据
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String result = data.getStringExtra("result");
                    Log.i(FirstActivity.class.getCanonicalName(), result);
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(FirstActivity.class.getCanonicalName(), "restart FirstActivity");
    }

    public static void actionStart(Context context, String data) {
        Intent intent = new Intent(context, FirstActivity.class);
        intent.putExtra("param", data);
        context.startActivity(intent);
    }
}
