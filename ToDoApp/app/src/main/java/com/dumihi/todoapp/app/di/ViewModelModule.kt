package com.dumihi.todoapp.app.di

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(factor: TodoViewModelFactory): ViewModelProvider.Factory
}