package com.mediasoftindonesia.serviceapps.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("api_token_refresh")
    @Expose
    int api_token_refresh;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getApi_token_refresh() {
        return api_token_refresh;
    }

    public void setApi_token_refresh(int api_token_refresh) {
        this.api_token_refresh = api_token_refresh;
    }

}
