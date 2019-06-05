package com.example.mvvmsample.data.models;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

public class GithubUser {
    private String login = "";

    @SerializedName("avatar_url")
    private String avatar;

    @Nullable
    public String getAvatar() {
        return avatar;
    }

    @Nullable
    public String getLogin() {
        return login;
    }
}
