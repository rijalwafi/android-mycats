package com.reg.home.kucingjoko;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.HashMap;

public class SessionManager {
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    private int PRIVATE_MODE = 0;
    public Context context;

    public static final String PREF_NAME = "LOGIN";
    public static final String LOGIN = "IS_LOGIN";
    public static final String ID_USER = "ID_USER";
    public static final String USERNAME = "USERNAME";
    public static final String ADDRESS = "ADDRESS";

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String id_user, String username, String address) {
        editor.putBoolean(LOGIN, true);
        editor.putString(ID_USER, id_user);
        editor.putString(USERNAME, username);
        editor.putString(ADDRESS, address);
        editor.apply();
    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean(LOGIN, false);
    }
        public void checkLogin() {
            if (!this.isLogin()) {
                Intent in = new Intent(context, SplashScreen.class);
                context.startActivity(in);
                //((MainActivity) context).finish();
                ((MainActivity) context).finish();

            }
        }
        public HashMap<String,String>getUserDetail(){
            HashMap<String,String>user=new HashMap<>();
            user.put(ID_USER,sharedPreferences.getString(ID_USER,null));
            user.put(USERNAME,sharedPreferences.getString(USERNAME,null));
            user.put(ADDRESS,sharedPreferences.getString(ADDRESS,null));
            return  user;
        }
        public void logout(){
        Intent logout=new Intent(context,LoginActivity.class);
        context.startActivity(logout);
        editor.clear();
        editor.commit();
        }
    }