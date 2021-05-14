package com.saikalyandaroju.daggerexample.di.module.main;

import com.saikalyandaroju.daggerexample.data.NetworkSource.models.Post;
import com.saikalyandaroju.daggerexample.ui.Profile.ProfileFragment;
import com.saikalyandaroju.daggerexample.ui.posts.PostsFragement;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributesProfileFragment();
    @ContributesAndroidInjector
    abstract PostsFragement contributesPostsFragment();
}
