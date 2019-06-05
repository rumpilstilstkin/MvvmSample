package com.example.mvvmsample.data.rest;

import com.example.mvvmsample.data.models.GithubUser;
import com.example.mvvmsample.data.models.RepsModel;

import java.util.List;

import io.reactivex.Single;

public interface NetApiClientInterface {
    Single<GithubUser> getUser(String user);
    Single<List<RepsModel>> getReps();
}
