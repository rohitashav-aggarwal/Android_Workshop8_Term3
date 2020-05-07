package com.example.android_term3_workshop8.storage;

import android.content.Context;
import android.content.SharedPreferences;

public class shareUser {
    private static final String Shared_Pref_Name = "my_shared_pref";
    private static shareUser mLoggedUser;
    private Context mUser;

    private shareUser(Context mUser){
        this.mUser = mUser;
    }

    public static shareUser getInstance(Context mUser){
        if(mLoggedUser == null){
            mLoggedUser = new shareUser(mUser);
        }
        return mLoggedUser;
    }

    public void saveUser(String Username){
        SharedPreferences sharedPreferences = mUser.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("Username", Username);

        editor.apply();
    }

    public boolean IsLoggedIn(){
        SharedPreferences sharedPreferences = mUser.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        if(sharedPreferences.getString("Username", "") != ""){
            return true;
        }
        return false;
    }

    public String getUser(){
        SharedPreferences sharedPreferences = mUser.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        String user = new String(
                sharedPreferences.getString("Username", "")
        );
        return user;
    }

    // used when we need to log out
    public void clear(){
        SharedPreferences sharedPreferences = mUser.getSharedPreferences(Shared_Pref_Name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
