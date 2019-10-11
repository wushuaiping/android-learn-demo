package io.wooo.uiwidgettest;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageView;

    private ProgressBar progressBar;

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        View button = findViewById(R.id.button);
        editText = findViewById(R.id.edit_text);
        imageView = findViewById(R.id.image_view);
        progressBar = findViewById(R.id.progress_bar);
        button.setOnClickListener(this);
    }

    /**
     * 除去匿名函数的方式来监听点击事件外，还可以通过实现OnClickListener接口来监听点击事件。
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                ProgressDialog dialog = new ProgressDialog(MainActivity.this);
                dialog.setCancelable(true);
                dialog.setTitle("ProgressDialog title");
                dialog.setMessage("加载中,请耐心等待....");
                dialog.show();
                break;
            default:
                break;
        }
    }
}
