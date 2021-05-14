package com.saikalyandaroju.daggerexample.ui.Profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.saikalyandaroju.daggerexample.R;
import com.saikalyandaroju.daggerexample.Utils.ApiResponse;
import com.saikalyandaroju.daggerexample.Utils.ViewModelProviderFactory;
import com.saikalyandaroju.daggerexample.data.NetworkSource.models.User;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {
    private static final String TAG = "ProfileFragment";
    private ProfileFramentViewModel profileFramentViewModel;
    private TextView username, email, website;
    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i("check", "profile fragment created..");
        //subscribeObservers();
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.i("check", "profile fragment view created..");
        super.onViewCreated(view, savedInstanceState);
        username = view.findViewById(R.id.username);
        email = view.findViewById(R.id.email);
        website = view.findViewById(R.id.website);
        profileFramentViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(ProfileFramentViewModel.class);
        subscribeObservers();
    }

    public void subscribeObservers() {
        profileFramentViewModel.getAuthUser().removeObservers(getViewLifecycleOwner());
        profileFramentViewModel.getAuthUser().observe(getViewLifecycleOwner(), new Observer<ApiResponse<User>>() {
            @Override
            public void onChanged(ApiResponse<User> userApiResponse) {
                Log.i(TAG, userApiResponse.data.getId() + "");
                if (userApiResponse != null) {
                    bindUserDetails(userApiResponse);
                } else {
                    showErrorStatus(userApiResponse);
                }
            }
        });
    }

    private void showErrorStatus(ApiResponse<User> userApiResponse) {
        email.setText(userApiResponse.errorDescription);
    }

    private void bindUserDetails(ApiResponse<User> userApiResponse) {
        username.setText(userApiResponse.data.getUsername());
        email.setText(userApiResponse.data.getEmail());
        website.setText(userApiResponse.data.getWebsite());
    }
}
