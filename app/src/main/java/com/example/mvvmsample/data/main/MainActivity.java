package com.example.mvvmsample.data.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mvvmsample.R;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    TextView login;
    ImageView image;
    Button reload;
    SwipeRefreshLayout refresh;

    View content;
    View error;

    UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userViewModel = new UserViewModel();
        initView();

        userViewModel.operationStatus.observe(this, operationStatus -> {
            switch (operationStatus) {
                case DONE:
                    showContent();
                    return;
                case ERROR:
                    showError();
                    return;
                case LODAING:
                    showProgress();
                    return;
            }
        });

        userViewModel.user.observe(this, githubUser -> {
            showUserName(githubUser.getLogin());
            showImage(githubUser.getAvatar());
        });

        userViewModel.updateData();

    }

    private void initView(){
        content = findViewById(R.id.contentView);
        error = findViewById(R.id.errorView);

        login = findViewById(R.id.username);
        image = findViewById(R.id.avatar);
        reload = findViewById(R.id.reload);
        refresh = findViewById(R.id.swipe);

        reload.setOnClickListener(v -> userViewModel.updateData());

        refresh.setOnRefreshListener(() -> userViewModel.updateData());

    }

    private void showContent() {
        error.setVisibility(View.GONE);
        refresh.setRefreshing(false);
        content.setVisibility(View.VISIBLE);
    }

    private void showError() {
        refresh.setRefreshing(false);
        content.setVisibility(View.GONE);
        error.setVisibility(View.VISIBLE);
    }

    private void showProgress() {
        error.setVisibility(View.GONE);
        refresh.setRefreshing(true);
    }

    private void showUserName(String name) {
        login.setText(name);
    }

    private void showImage(String url) {
        Picasso.get()
                .load(url)
                .into(image);
    }
}
