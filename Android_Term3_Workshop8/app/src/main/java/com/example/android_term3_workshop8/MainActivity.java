package com.example.android_term3_workshop8;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_term3_workshop8.RestServices.loginApi;
import com.example.android_term3_workshop8.RestServices.testApi;
import com.example.android_term3_workshop8.models.Login;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String Logged_User = "Username";
    private loginApi loginApi;
    private EditText username;
    private EditText password;
    private Button login;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String url = "http://192.168.1.67:9493/Servlets_Term3_RESTfulAPI_Workshop7_war_exploded/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        loginApi = retrofit.create(com.example.android_term3_workshop8.RestServices.loginApi.class);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login_button);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                loginUser(username.getText().toString(), password.getText().toString());
                LoggedUserToEditorActivity(username.getText().toString());
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void loginUser(final String username, String password){

        String token = username+ ":"+ password;
        String hash = Base64.encodeToString(token.getBytes(), Base64.DEFAULT);
        String updatedHash = "basic " + hash.substring(0, hash.length()-1);
        Call<ResponseBody> call = loginApi.loginUser(updatedHash);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(!response.isSuccessful()){
                    Log.e("onResponse: error", String.valueOf(response.code()));
                    return;
                }else if(response.code() == 200){
                    Toast.makeText(getApplicationContext(), "Welcome " + username, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("onResponse: Failure", String.valueOf(t.getMessage()));
            }
        });
    }

    private void LoggedUserToEditorActivity(String Username){
        Intent intent = new Intent(this, EditorActivity.class);
        intent.putExtra(Logged_User, Username);
        startActivity(intent);
    }
}