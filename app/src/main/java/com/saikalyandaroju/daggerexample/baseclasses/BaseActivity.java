package com.saikalyandaroju.daggerexample.baseclasses;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.saikalyandaroju.daggerexample.MainActivity;
import com.saikalyandaroju.daggerexample.SessionManager;
import com.saikalyandaroju.daggerexample.Utils.ApiResponse;
import com.saikalyandaroju.daggerexample.data.NetworkSource.models.User;
import com.saikalyandaroju.daggerexample.ui.Auth.AuthActivity;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class BaseActivity extends DaggerAppCompatActivity {
    private static final String TAG = "BaseActivity";
    @Inject
    SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObservers();
    }
    public void subscribeObservers(){
        if(sessionManager.getAuthUser()==null){
            navigateTologinScreen();
        }
        else {
            sessionManager.getAuthUser().observe(this, new Observer<ApiResponse<User>>() {
                @Override
                public void onChanged(ApiResponse<User> userApiResponse) {
                    if(userApiResponse.status==0){
                        Toast.makeText(getApplicationContext(),"failed to login",Toast.LENGTH_SHORT).show();
                    }
                    else {

                       // Toast.makeText(getApplicationContext(),"Login success",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }
    }

    private void navigateTologinScreen() {
        Intent intent=new Intent(this, AuthActivity.class);
        startActivity(intent);
        finish();
    }
}
