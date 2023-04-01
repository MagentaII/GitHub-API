package com.example.dcardhomework.di;

import com.example.dcardhomework.MainActivity;
import com.example.dcardhomework.ui.HomeFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {

    @ContributesAndroidInjector
    abstract MainActivity contributeMainActivity();

    @ContributesAndroidInjector
    abstract HomeFragment contributeHomeFragment();
}
