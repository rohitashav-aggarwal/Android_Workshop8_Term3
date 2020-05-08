/*
Author - Rohit
Android - Term 3 Project
 */

package com.example.android_term3_workshop8;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_term3_workshop8.RestServices.RetrofitClient;
import com.example.android_term3_workshop8.RestServices.loginApi;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private loginApi loginApi;
    private EditText firstName, lastName, address, city, province, postal, country, homePhone, busPhone, email, username, password;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        Toast.makeText(this, "Register here", Toast.LENGTH_SHORT).show();
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        address = findViewById(R.id.Address);
        city = findViewById(R.id.City);
        province = findViewById(R.id.Province);
        postal = findViewById(R.id.PostalCode);
        country = findViewById(R.id.Country);
        homePhone = findViewById(R.id.HomePhone);
        busPhone = findViewById(R.id.BusPhone);
        email = findViewById(R.id.Email);
        username = findViewById(R.id.User_Name);
        password = findViewById(R.id.Pass_word);

        findViewById(R.id.Register_Button).setOnClickListener(this);
        findViewById(R.id.textViewLogin).setOnClickListener(this);
    }

    private void newUser() {
        String uFirstName = firstName.getText().toString().trim();
        String uLastName = lastName.getText().toString().trim();
        String uAddress = address.getText().toString().trim();
        String uCity = city.getText().toString().trim();
        String uProvince = province.getText().toString().trim();
        String uPostal = postal.getText().toString().trim();
        String uCountry = country.getText().toString().trim();
        String uHomePhone = homePhone.getText().toString().trim();
        String uBusPhone = busPhone.getText().toString().trim();
        String uEmail = email.getText().toString().trim();
        String uUsername = username.getText().toString().trim();
        String uPassword = password.getText().toString().trim();

        if (uFirstName.isEmpty()) {
            firstName.setError("First name is required");
            firstName.requestFocus();
            return;
        }
        if (uLastName.isEmpty()) {
            lastName.setError("Last name is required");
            lastName.requestFocus();
            return;
        }
        if (uAddress.isEmpty()) {
            address.setError("Address is required");
            address.requestFocus();
            return;
        }
        if (uCity.isEmpty()) {
            city.setError("City is required");
            city.requestFocus();
            return;
        }
        if (uProvince.length() != 2) {
            province.setError("Province code is required");
            province.requestFocus();
            return;
        }
        if (uPostal.length() != 6) {
            postal.setError("Valid 6 char Postal is required");
            postal.requestFocus();
            return;
        }
        if (uCountry.isEmpty()) {
            country.setError("Country is required");
            country.requestFocus();
            return;
        }
        if (uHomePhone.isEmpty()) {
            homePhone.setError("Enter a valid phone number");
            homePhone.requestFocus();
            return;
        }
        if (uBusPhone.isEmpty()) {
            busPhone.setError("Enter a valid phone number");
            busPhone.requestFocus();
            return;
        }
        if (uEmail.isEmpty()) {
            email.setError("Enter a valid email");
            email.requestFocus();
            return;
        }
        if (uUsername.isEmpty()) {
            username.setError("Username is required");
            username.requestFocus();
            return;
        }
        if (uPassword.isEmpty()) {
            password.setError("Password is required");
            password.requestFocus();
            return;
        }

        Call<ResponseBody> call = RetrofitClient.getInstance().getAPI()
                .registerUser(uFirstName, uLastName, uAddress, uCity, uProvince, uPostal, uCountry
                        , uHomePhone, uBusPhone, uEmail, uUsername, uPassword);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String s = response.body().string();
                    Toast.makeText(RegisterActivity.this, "Registration Complete", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("onFailure: in failure", t.getCause() + "2" + t.getStackTrace());
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Register_Button:
                newUser();
                break;
            case R.id.textViewLogin:
                startActivity(new Intent(this, MainActivity.class));
                break;
        }
    }
}
