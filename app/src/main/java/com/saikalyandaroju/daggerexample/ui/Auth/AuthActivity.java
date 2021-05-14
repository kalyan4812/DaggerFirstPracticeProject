package com.saikalyandaroju.daggerexample.ui.Auth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.RequestManager;
import com.google.android.material.imageview.ShapeableImageView;
import com.saikalyandaroju.daggerexample.MainActivity;
import com.saikalyandaroju.daggerexample.R;
import com.saikalyandaroju.daggerexample.SessionManager;
import com.saikalyandaroju.daggerexample.Utils.ApiResponse;
import com.saikalyandaroju.daggerexample.Utils.ViewModelProviderFactory;
import com.saikalyandaroju.daggerexample.data.NetworkSource.models.User;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity {
    @Inject
    Drawable drawable;
    @Inject
    RequestManager requestManager;
    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    private AuthViewModel authViewModel;

    private EditText userId;
    private Button loginbutton;
    private ProgressDialog progressDialog;
    @Inject
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        userId=findViewById(R.id.user_id_input);
        loginbutton=findViewById(R.id.login_button);
        progressDialog=new ProgressDialog(this);
        authViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(AuthViewModel.class);
        setlogo();
        setupObservers();
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!TextUtils.isEmpty(userId.getText().toString())){
                    authViewModel.authenticateWithId(Integer.parseInt(userId.getText().toString()));

                }
            }
        });
        sessionManager.loadingStatus.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    showProgressBar();
                } else {
                    progressDialog.dismiss();
                }
            }
        });
    }

    private void showProgressBar() {
        progressDialog.show();
    }

    private void setupObservers() {
        authViewModel.observeUser().observe(this, new Observer<ApiResponse<User>>() {
            @Override
            public void onChanged(ApiResponse<User> userApiResponse) {
                if(userApiResponse.status==0){
                    Toast.makeText(getApplicationContext(),"failed to login",Toast.LENGTH_SHORT).show();
                }
                else if(userApiResponse.status==1) {
                    Log.i("auth","enterd");
                    Intent i=new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(i);
                    Toast.makeText(getApplicationContext(),"Login success",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }

    private void setlogo() {
        requestManager.load(drawable).into((ShapeableImageView) findViewById(R.id.login_logo));
    }
}
