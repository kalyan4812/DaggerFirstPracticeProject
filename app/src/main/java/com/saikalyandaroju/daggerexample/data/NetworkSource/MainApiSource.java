package com.saikalyandaroju.daggerexample.data.NetworkSource;

import com.saikalyandaroju.daggerexample.data.NetworkSource.models.Post;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainApiSource {
    @GET("posts")
    Flowable<List<Post>> getPostsFromUser(@Query("userId") int userId);
}
