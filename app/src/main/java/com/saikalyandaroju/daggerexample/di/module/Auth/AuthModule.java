package com.saikalyandaroju.daggerexample.di.module.Auth;

import com.saikalyandaroju.daggerexample.data.NetworkSource.ApiSource;
import com.saikalyandaroju.daggerexample.di.scopes.AuthScope;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class AuthModule {


    @AuthScope
    @Provides
   static ApiSource provideApiSource(Retrofit retrofit){
        return retrofit.create(ApiSource.class);
    }
    // retrofit instance is accesible here because we have defined this module in the AuthComponenet
    // and authcomponent is subcomponenent of appcomponent which can access retrofit from network module.
}
