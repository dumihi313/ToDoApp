package com.dumihi.todoapp.app.di

import com.dumihi.todoapp.app.presentation.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class MainModule {
    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeMainFragment(): MainFragment
}