package com.dumihi.todoapp.app.di.appcontext

import android.content.Context
import com.dumihi.todoapp.app.TodoApplication
import com.dumihi.todoapp.app.di.appcontext.AppContextScoped
import dagger.Module
import dagger.Provides

@Module
class ContextModule {
    @Provides
    @AppContextScoped
    fun provideContext(application: TodoApplication): Context = application.applicationContext
}