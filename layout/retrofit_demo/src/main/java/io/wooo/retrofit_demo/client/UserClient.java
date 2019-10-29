package io.wooo.retrofit_demo.client;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface UserClient {

    @FormUrlEncoded
    @POST("index/login")
    Call<ResponseBody> login(@Field("username") String user, @Field("password") String password);

}
