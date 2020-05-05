package com.example.android_term3_workshop8.RestServices;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface loginApi {
    @POST("api/loginuser")
    Call<ResponseBody> loginUser(@Header("authorization") String token);

}
