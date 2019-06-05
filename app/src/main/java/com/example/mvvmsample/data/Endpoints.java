package com.example.mvvmsample.data;

import com.example.mvvmsample.data.models.GithubUser;
import com.example.mvvmsample.data.models.RepsModel;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Endpoints {

    @GET("/users/{user}")
    Single<GithubUser> getUser(
            @Path("user") String user
    );

    @GET("/repositories")
    Single<List<RepsModel>> getRepos();
}
