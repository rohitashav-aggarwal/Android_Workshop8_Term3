package com.example.android_term3_workshop8.RestServices;

import retrofit2.Call;
import retrofit2.http.GET;

public interface testApi {
    @GET("api/testapi")
    Call<String> testApi();
}
