package com.saikalyandaroju.daggerexample;

import android.net.IpSecManager;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.saikalyandaroju.daggerexample.Utils.ApiResponse;
import com.saikalyandaroju.daggerexample.Utils.SingleLiveEvent;
import com.saikalyandaroju.daggerexample.data.NetworkSource.models.User;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {
    private static final String TAG = "SessionManager";
    private MediatorLiveData<ApiResponse<User>> cacheduser = new MediatorLiveData<>();
    public SingleLiveEvent<Boolean> loadingStatus = new SingleLiveEvent<>();

    @Inject
    public SessionManager() {
    }

    public void authenticatewithId(final LiveData<ApiResponse<User>> source) {
        if (cacheduser != null) {
            loadingStatus.setValue(true);
            cacheduser.addSource(source, new Observer<ApiResponse<User>>() {
                @Override
                public void onChanged(ApiResponse<User> userApiResponse) {
                    cacheduser.setValue(userApiResponse);
                    cacheduser.removeSource(source);
                    loadingStatus.setValue(false);
                }
            });
        }

    }

    public void logout() {
        cacheduser.setValue(null);
    }

    public LiveData<ApiResponse<User>> getAuthUser() {
        return cacheduser;
    }
}
