package com.saikalyandaroju.daggerexample.di.component;

import android.app.Application;

import com.saikalyandaroju.daggerexample.MyApplication;
import com.saikalyandaroju.daggerexample.SessionManager;
import com.saikalyandaroju.daggerexample.di.module.ActivityBuildersModule;
import com.saikalyandaroju.daggerexample.di.module.AppModule;
import com.saikalyandaroju.daggerexample.di.module.ViewModelProviderFactoryModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;

// to use Andriod Injector class we need to import modules like AndroidSupportInjectionModule class.
// Andriod injector is to make connnection b/w MyApplication("client") & AppComponent("Server") its just replacement of void inject(MyApplication myapplication)

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class, ActivityBuildersModule.class, AppModule.class, ViewModelProviderFactoryModule.class,

})
public interface AppComponent extends AndroidInjector<MyApplication> {
    SessionManager sessionManager();

    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance Application application);
    }
}

