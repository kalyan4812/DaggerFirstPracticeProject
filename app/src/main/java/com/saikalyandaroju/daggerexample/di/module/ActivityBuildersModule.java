package com.saikalyandaroju.daggerexample.di.module;

import com.saikalyandaroju.daggerexample.MainActivity;
import com.saikalyandaroju.daggerexample.di.module.Auth.AuthModule;
import com.saikalyandaroju.daggerexample.di.module.AuthViewModelModule;
import com.saikalyandaroju.daggerexample.di.module.main.MainFragmentBuildersModule;
import com.saikalyandaroju.daggerexample.di.module.main.MainModule;
import com.saikalyandaroju.daggerexample.di.module.main.MainViewModelModules;
import com.saikalyandaroju.daggerexample.di.scopes.AuthScope;
import com.saikalyandaroju.daggerexample.di.scopes.MainScope;
import com.saikalyandaroju.daggerexample.ui.Auth.AuthActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule {


    @AuthScope
    @ContributesAndroidInjector(modules = {AuthViewModelModule.class, AuthModule.class})
    abstract AuthActivity contributeAuthActivity();

    @MainScope
    @ContributesAndroidInjector(modules = {MainFragmentBuildersModule.class, MainViewModelModules.class, MainModule.class})
    abstract MainActivity contributeMainActivity();
}
