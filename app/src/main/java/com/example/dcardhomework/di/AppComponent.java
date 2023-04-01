package com.example.dcardhomework.di;

import com.example.dcardhomework.GithubApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class, // 如果不是用support library的話就改成AndroidInjectionModule
        AppModule.class,
        BuildersModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(GithubApp application);
        AppComponent build();
    }

    void inject(GithubApp app);
}
