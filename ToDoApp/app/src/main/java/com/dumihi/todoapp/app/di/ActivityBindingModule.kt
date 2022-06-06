package com.dumihi.todoapp.app.di

import com.dumihi.todoapp.app.presentation.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            SellModule::class,
            BuyModule::class,
            CallModule::class,
            MainModule::class,
        ]
    )
    internal abstract fun contributeMainActivity(): MainActivity
}