package com.example.android_term3_workshop8;

import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.internal.NavigationMenu;

public class EditorActivity extends AppCompatActivity {

    // user that logged in
    String user;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editor_activity);
        user = getIntent().getExtras().getString(MainActivity.Logged_User);
        Toast.makeText(this, "Welcome " + user, Toast.LENGTH_LONG).show();
    }
}
