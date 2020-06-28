package com.mediasoftindonesia.serviceapps.token;

import android.content.SharedPreferences;

import com.mediasoftindonesia.serviceapps.model.User;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

public class TokenManager {

    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    private static TokenManager INSTANCE = null;

    private TokenManager(SharedPreferences prefs) {
        this.prefs = prefs;
        this.editor = prefs.edit();
    }

    public static synchronized TokenManager getInstance(SharedPreferences prefs) {
        if (INSTANCE == null) {
            INSTANCE = new TokenManager(prefs);
        }
        return INSTANCE;
    }

    public void saveToken(User token) {
        editor.putString("API_TOKEN", token.getToken()).commit();
    }

    public void deleteToken() {
        editor.remove("API_TOKEN").commit();
    }

    public User getToken() {
        User token = new User();
        token.setToken(prefs.getString("API_TOKEN", null));
        return token;
    }

}
