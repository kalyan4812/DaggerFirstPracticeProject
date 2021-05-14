package com.saikalyandaroju.daggerexample.data.NetworkSource;

import com.saikalyandaroju.daggerexample.data.NetworkSource.models.Post;
import com.saikalyandaroju.daggerexample.data.NetworkSource.models.User;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiSource {

    @GET("users/{id}")
    Flowable<User> getUserData(@Path("id") int id);


}
