/*
Author - Rohit
Android - Term 3 Project
 */

package com.example.android_term3_workshop8;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.android_term3_workshop8.Fragments.BookingsFragment;
import com.example.android_term3_workshop8.Fragments.PackagesFragment;
import com.example.android_term3_workshop8.Fragments.ProfileFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class EditorActivity extends AppCompatActivity implements  BottomNavigationView.OnNavigationItemSelectedListener{

    // user that is logged in
    String user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_activity);
//        findViewById(R.id.profile)
        user = getIntent().getExtras().getString(MainActivity.Logged_User);
        Toast.makeText(this, "Welcome " + user, Toast.LENGTH_LONG).show();

        BottomNavigationView navigationView = findViewById(R.id.bottom_nav);
        navigationView.setOnNavigationItemSelectedListener(this);

        displayFragment(new ProfileFragment());
    }

    private void displayFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.editorActivity, fragment)
                .commit();
    }

    private void logOff(){
        //shareUser.getInstance(getParent()).clear();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch(item.getItemId()){
            case R.id.profile:
                fragment = new ProfileFragment();
                break;
            case R.id.packages:
                fragment = new PackagesFragment();
                break;
            case R.id.bookings:
                fragment = new BookingsFragment();
                break;
            case R.id.logoff:
                logOff();
                break;
        }
        if(fragment != null){
            displayFragment(fragment);
        }
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
