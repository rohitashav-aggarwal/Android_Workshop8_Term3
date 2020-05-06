package com.example.android_term3_workshop8.RestServices;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface loginApi {
    @POST("api/loginuser")
    Call<ResponseBody> loginUser(@Header("authorization") String token);

    @POST("api/registeruser")
    Call<ResponseBody> registerUser(@Header("firstName") String firstname,
                                           @Header("lastName") String lastname,
                                           @Header("address") String address,
                                           @Header("city") String city,
                                           @Header("province") String province,
                                           @Header("postal") String postal,
                                           @Header("country") String country,
                                           @Header("homephone") String homephone,
                                           @Header("busphone") String busphone,
                                           @Header("email") String email,
                                           @Header("username") String username,
                                           @Header("password") String password);
}
