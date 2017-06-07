package com.kerse.markproject.common;


import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class Prefrences {

    private SharedPreferences.Editor editor;
    SharedPreferences prefs;

    private String MY_PREFS_NAME = "pref";

    public Prefrences(Context context) {
        this.editor = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        this.prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
    }

    public void saveID(String id){
        editor.putString("id", id);
        editor.commit();
    }

    public String loadID(){
        return prefs.getString("id", null);
    }

    public void saveToken(String token){
        editor.putString("token", token);
        editor.commit();
    }

    public void saveLogin(boolean isLogin){
        editor.putBoolean("isLogin",isLogin);
        editor.commit();
    }

    public String loadToken(){
       return prefs.getString("token", null);
    }

    public boolean loadLogin(){
        return prefs.getBoolean("isLogin",false);
    }
}
