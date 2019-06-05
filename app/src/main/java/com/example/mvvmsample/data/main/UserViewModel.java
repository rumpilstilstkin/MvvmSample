package com.example.mvvmsample.data.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmsample.data.models.GithubUser;
import com.example.mvvmsample.data.rest.NetApiClient;
import com.example.mvvmsample.data.utils.OperationStatus;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;

public class UserViewModel extends ViewModel {
    MutableLiveData<OperationStatus> operationStatus = new MutableLiveData();
    MutableLiveData<GithubUser> user = new MutableLiveData();

    public void updateData() {
        operationStatus.postValue(OperationStatus.LODAING);

        Disposable d = NetApiClient.getInstance().getUser("rumpilstilstkin")
                .subscribeWith(new DisposableSingleObserver<GithubUser>() {
                    @Override
                    public void onSuccess(GithubUser githubUser) {
                        user.postValue(githubUser);
                        operationStatus.postValue(OperationStatus.DONE);
                    }

                    @Override
                    public void onError(Throwable e) {
                        operationStatus.postValue(OperationStatus.ERROR);
                    }
                });
    }
}
