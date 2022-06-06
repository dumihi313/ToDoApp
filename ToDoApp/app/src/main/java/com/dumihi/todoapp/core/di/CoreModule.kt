package com.dumihi.todoapp.core.di

import android.content.Context
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import com.dumihi.todoapp.core.AppConfigStorage
import com.dumihi.todoapp.core.Configs

@Module
class CoreModule {
    @Provides
    @CoreScoped
    fun provideAppConfigs(appConfigStorage: AppConfigStorage): Configs =
        appConfigStorage.currentConfig

    @Provides
    fun provideAppConfigsStorage(context: Context): AppConfigStorage =
        AppConfigStorage.getInstance(Pair(context, GsonBuilder().create()))

}