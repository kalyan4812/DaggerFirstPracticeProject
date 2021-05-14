package com.saikalyandaroju.daggerexample.di.module;

import androidx.lifecycle.ViewModelProvider;

import com.saikalyandaroju.daggerexample.Utils.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public  abstract class ViewModelProviderFactoryModule {
    @Binds
    public abstract ViewModelProvider.Factory bindViewModelFacory(ViewModelProviderFactory viewModelProviderFactory);
}
