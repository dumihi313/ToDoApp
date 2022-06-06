package com.dumihi.todoapp.core.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import com.dumihi.todoapp.app.di.appcontext.ContextComponent
import com.dumihi.todoapp.core.AppConfigStorage
import com.dumihi.todoapp.core.Configs

@CoreScoped
@Component(
    modules = [CoreModule::class],
    dependencies = [ContextComponent::class],
)
interface CoreComponent {
    @RemoteHost
    fun remoteHost(): String

    @PlatformCode
    fun platform(): String

    @AppVersion
    fun versions(): String

    fun appConfigStorage(): AppConfigStorage

    fun configsData(): Configs

    fun context(): Context

    @Component.Factory
    interface Factory {
        fun create(
            contextComponent: ContextComponent,
            @BindsInstance @RemoteHost host: String,
            @BindsInstance @PlatformCode platform: String,
            @BindsInstance @AppVersion appVersion: String,
        ): CoreComponent
    }
}