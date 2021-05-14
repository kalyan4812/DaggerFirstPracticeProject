package com.saikalyandaroju.daggerexample.ui.Profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.saikalyandaroju.daggerexample.SessionManager;
import com.saikalyandaroju.daggerexample.Utils.ApiResponse;
import com.saikalyandaroju.daggerexample.Utils.ViewModelProviderFactory;
import com.saikalyandaroju.daggerexample.data.NetworkSource.models.User;

import javax.inject.Inject;

public class ProfileFramentViewModel  extends ViewModel {
    private static final String TAG = "ProfileFramentViewModel";

   private SessionManager sessionManager;

    @Inject
    public ProfileFramentViewModel(SessionManager sessionManager) {
        this.sessionManager=sessionManager;
       // Log.i(TAG,"profile viewmodel created");
    }

    public LiveData<ApiResponse<User>> getAuthUser(){
        return sessionManager.getAuthUser();
    }
}
