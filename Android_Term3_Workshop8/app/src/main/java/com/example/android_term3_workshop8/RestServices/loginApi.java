package com.example.android_term3_workshop8.RestServices;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
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
    @GET("api/getCustomer")
    Call<ResponseBody> profileUser(@Header("username") String username);

    @POST("api/updateCustomer")
    Call<ResponseBody> updateCustomer(@Header("id") int id,
                                      @Header("firstName") String firstname,
                                      @Header("email") String email,
                                      @Header("username") String username,
                                      @Header("password") String password);
}
