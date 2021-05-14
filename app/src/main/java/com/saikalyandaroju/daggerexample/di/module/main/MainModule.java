package com.saikalyandaroju.daggerexample.di.module.main;

import com.saikalyandaroju.daggerexample.data.NetworkSource.ApiSource;
import com.saikalyandaroju.daggerexample.data.NetworkSource.MainApiSource;
import com.saikalyandaroju.daggerexample.di.scopes.MainScope;
import com.saikalyandaroju.daggerexample.ui.posts.PostsRecyclerAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.saikalyandaroju.daggerexample.data.NetworkSource.ApiEndPoints.BASE_URL;

@Module
public class MainModule {

    @MainScope
    @Provides
    static MainApiSource provideMainApiSource(Retrofit retrofit){
        return retrofit.create(MainApiSource.class);
    }

    @MainScope
    @Provides
    static PostsRecyclerAdapter providepostsRecyclerAdapter(){
        return new PostsRecyclerAdapter();
    }
}
