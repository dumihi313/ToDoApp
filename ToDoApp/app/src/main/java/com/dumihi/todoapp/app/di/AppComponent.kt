package com.dumihi.todoapp.app.di

import com.dumihi.todoapp.app.TodoApplication
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import com.dumihi.todoapp.data.di.DataComponent

@AppScoped
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        ActivityBindingModule::class,
        AppModule::class,
    ],
    dependencies = [DataComponent::class]
)
interface AppComponent : AndroidInjector<TodoApplication> {

    @Component.Factory
    interface Factory {
        fun create(
            dataComponent: DataComponent
        ): AppComponent
    }
}