package com.saikalyandaroju.daggerexample.ui.Auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.saikalyandaroju.daggerexample.SessionManager;
import com.saikalyandaroju.daggerexample.Utils.ApiResponse;
import com.saikalyandaroju.daggerexample.Utils.SingleLiveEvent;
import com.saikalyandaroju.daggerexample.data.NetworkSource.ApiSource;
import com.saikalyandaroju.daggerexample.data.NetworkSource.models.User;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {


    private final ApiSource apiSource;
    private final SessionManager sessionManager;

    @Inject
    public AuthViewModel(ApiSource apiSource, SessionManager sessionManager) {

        this.apiSource = apiSource;
        this.sessionManager = sessionManager;

    }

    public void authenticateWithId(int userId) {

        sessionManager.authenticatewithId(querywithId(userId));
    }

    public LiveData<ApiResponse<User>> querywithId(int id) {
        return LiveDataReactiveStreams.fromPublisher(
                apiSource.getUserData(id).
                        onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(@NonNull Throwable throwable) throws Exception {
                                User erroruser = new User();
                                erroruser.setId(-1);
                                return erroruser;
                            }
                        }).map(new Function<User, ApiResponse<User>>() {
                    @Override
                    public ApiResponse<User> apply(@NonNull User user) throws Exception {
                        if (user.getId() == -1) {
                            return new ApiResponse(0, "failed...", null);
                        }
                        return new ApiResponse<>(1, user, null);
                    }
                }).
                        subscribeOn(Schedulers.io())
        );
    }

    public LiveData<ApiResponse<User>> observeUser() {
        return sessionManager.getAuthUser();
    }
}
