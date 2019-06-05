package com.example.mvvmsample.data.rest;

import com.example.mvvmsample.data.Endpoints;
import com.example.mvvmsample.data.models.GithubUser;
import com.example.mvvmsample.data.models.RepsModel;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class NetApiClient implements NetApiClientInterface {

    private static NetApiClient ourInstance;

    public static NetApiClient getInstance() {
        if (ourInstance == null) {
            ourInstance = new NetApiClient();
        }
        return ourInstance;
    }

    private Endpoints netApi = new ServiceGenerator().createService(Endpoints.class);

    private NetApiClient() {
    }

    @Override
    public Single<GithubUser> getUser(String user) {
        return netApi.getUser(user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Single<List<RepsModel>> getReps() {
        return netApi.getRepos()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
