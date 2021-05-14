package com.saikalyandaroju.daggerexample.di.module.main;

import androidx.lifecycle.ViewModel;

import com.saikalyandaroju.daggerexample.di.key.ViewModelKey;
import com.saikalyandaroju.daggerexample.ui.Profile.ProfileFramentViewModel;
import com.saikalyandaroju.daggerexample.ui.posts.PostViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModules {
    @Binds
    @IntoMap
    @ViewModelKey(ProfileFramentViewModel.class)
    public abstract ViewModel bindProfileviewmodel(ProfileFramentViewModel profileFramentViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostViewModel.class)
    public abstract ViewModel bindPostviewmodel(PostViewModel postViewModel);
}
