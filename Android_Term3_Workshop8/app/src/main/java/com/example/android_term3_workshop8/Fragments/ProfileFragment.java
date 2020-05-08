/*
Author - Rohit
Android - Term 3 Project
 */

package com.example.android_term3_workshop8.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android_term3_workshop8.R;
import com.example.android_term3_workshop8.RestServices.RetrofitClient;
import com.example.android_term3_workshop8.models.Customers;
import com.example.android_term3_workshop8.storage.shareUser;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private EditText textViewId, textViewEmail, textViewName, textViewUsername, textViewPassword;
    private Button updateButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textViewId = view.findViewById(R.id.textViewId);
        textViewName = view.findViewById(R.id.textViewFirstName);
        textViewEmail = view.findViewById(R.id.textViewEmail);
        textViewUsername = view.findViewById(R.id.textViewUsername);
        textViewPassword = view.findViewById(R.id.textViewPassword);
        displayProfile();
        view.findViewById(R.id.Update_Button).setOnClickListener(this);
    }

    public void displayProfile() {
        Call<Customers> call = RetrofitClient.getInstance().getAPI()
                .profileUser(shareUser.getInstance(getActivity()).getUser());
        call.enqueue(new Callback<Customers>() {
            @Override
            public void onResponse(Call<Customers> call, Response<Customers> response) {
                Customers customer = response.body();
                textViewId.setText(String.valueOf(customer.getCustomerId()));
                textViewName.setText(customer.getCustFirstName());
                textViewEmail.setText(customer.getCustEmail());
                textViewUsername.setText(customer.getUsersEntity().getUsername());
                textViewPassword.setText(customer.getUsersEntity().getPassword());

                Toast.makeText(getActivity(), "User profile", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Customers> call, Throwable t) {

            }
        });
    }

    public void updateProfile() {
        int uId = Integer.parseInt(textViewId.getText().toString());
        String uFirstName = textViewName.getText().toString().trim();
        String uEmail = textViewEmail.getText().toString().trim();
        final String uUsername = textViewUsername.getText().toString().trim();
        String uPassword = textViewPassword.getText().toString().trim();

        if (uFirstName.isEmpty()) {
            textViewName.setError("First name is required");
            textViewName.requestFocus();
            return;
        }
        if (uEmail.isEmpty()) {
            textViewEmail.setError("Enter a valid email");
            textViewEmail.requestFocus();
            return;
        }
        if (uUsername.isEmpty()) {
            textViewUsername.setError("Username is required");
            textViewUsername.requestFocus();
            return;
        }
        if (uPassword.isEmpty()) {
            textViewPassword.setError("Password is required");
            textViewPassword.requestFocus();
            return;
        }

        Call<ResponseBody> call = RetrofitClient.getInstance().getAPI()
                .updateCustomer(uId, uFirstName, uEmail, uUsername, uPassword);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Toast.makeText(getActivity(), "Customer Updated " + uUsername, Toast.LENGTH_LONG).show();
                if (response.isSuccessful() || response.code() == 200) {
                    shareUser.getInstance(getActivity()).saveUser(uUsername);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("onFailure: in failure", t.getCause() + "2" + t.getStackTrace());
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.Update_Button) {
            updateProfile();
        }
    }
}
