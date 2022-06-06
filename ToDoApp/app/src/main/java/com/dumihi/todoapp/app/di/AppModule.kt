package com.dumihi.todoapp.app.di

import android.content.Context
import android.content.SharedPreferences
import coil.ImageLoader
import coil.imageLoader
import com.dumihi.todoapp.app.local.UserLocalStorage
import com.dumihi.todoapp.data.local.UserStorage
import dagger.Module
import dagger.Provides
import com.dumihi.todoapp.data.di.UserPrefs

@Module
class AppModule {

    @Provides
    @AppScoped
    fun provideImageLoader(context: Context): ImageLoader = context.imageLoader

    @Provides
    @UserPrefs
    fun providesUserPref(context: Context): SharedPreferences {
        return context.applicationContext.getSharedPreferences(
            "user_prefs",
            Context.MODE_PRIVATE
        ) // todo: check necessary
    }

    @Provides
    fun provideUserStorage(@UserPrefs pref: SharedPreferences): UserStorage =
        UserLocalStorage(pref) // todo: check necessary
}