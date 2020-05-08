/*
Author - Rohit
Android - Term 3 Project
 */

package com.example.android_term3_workshop8.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_term3_workshop8.Adapters.PackagesAdapter;
import com.example.android_term3_workshop8.R;
import com.example.android_term3_workshop8.RestServices.RetrofitClient;
import com.example.android_term3_workshop8.models.Packages;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PackagesFragment extends Fragment {

    private List<Packages> listView;
    private RecyclerView recyclerView;
    private PackagesAdapter packagesAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.recyclerview_packages, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        displayPackages();
    }

    private void displayPackages() {
        Call<List<Packages>> call = RetrofitClient.getInstance().getAPI().getPackage();
        call.enqueue(new Callback<List<Packages>>() {
            @Override
            public void onResponse(Call<List<Packages>> call, Response<List<Packages>> response) {
                listView = response.body();
                packagesAdapter = new PackagesAdapter(getActivity(), listView);
                recyclerView.setAdapter(packagesAdapter);
//                List<Packages> packages = response.body();
//                ArrayList<String> packDetails = new ArrayList<>();
//                for(int i = 0; i < packages.size(); i++) {
//                    packDetails.add(packages.toString());
//                }
//                listView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,packDetails));
                Toast.makeText(getActivity(), "Packages", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<List<Packages>> call, Throwable t) {

            }
        });
    }
}
