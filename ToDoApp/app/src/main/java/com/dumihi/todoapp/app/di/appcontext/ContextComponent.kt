package com.dumihi.todoapp.app.di.appcontext

import android.content.Context
import com.dumihi.todoapp.app.TodoApplication
import dagger.BindsInstance
import dagger.Component

@AppContextScoped
@Component(modules = [ContextModule::class])
interface ContextComponent {
    fun context(): Context

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance liveApplication: TodoApplication): ContextComponent
    }
}