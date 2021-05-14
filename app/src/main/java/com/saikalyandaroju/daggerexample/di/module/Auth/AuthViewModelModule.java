package com.saikalyandaroju.daggerexample.di.module;

import androidx.lifecycle.ViewModel;

import com.saikalyandaroju.daggerexample.di.key.ViewModelKey;
import com.saikalyandaroju.daggerexample.ui.Auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel authViewModel);
}
