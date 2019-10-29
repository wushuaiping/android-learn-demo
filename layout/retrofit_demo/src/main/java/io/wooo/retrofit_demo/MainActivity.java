package io.wooo.retrofit_demo;

import androidx.appcompat.app.AppCompatActivity;
import io.wooo.retrofit_demo.client.UserClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;

    private UserClient userClient;

    private EditText usernameEt;

    private EditText passwordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:9999")
                .build();

        userClient = retrofit.create(UserClient.class);

        usernameEt = findViewById(R.id.et_username);

        passwordEt = findViewById(R.id.et_password);
    }

    public void loginClick(View view){
        Call<ResponseBody> loginCall = userClient.login(usernameEt.getText().toString(), passwordEt.getText().toString());
        loginCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String string = response.body().string();
                    System.out.println(string);
                    Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println(t);
            }
        });
    }
}
