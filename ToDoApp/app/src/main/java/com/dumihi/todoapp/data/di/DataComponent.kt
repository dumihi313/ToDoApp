package com.dumihi.todoapp.data.di

import android.content.Context
import com.dumihi.todoapp.app.utils.ConnectionManager
import com.dumihi.todoapp.core.di.AppVersion
import com.dumihi.todoapp.core.di.PlatformCode
import com.dumihi.todoapp.data.local.UserStorage
import com.dumihi.todoapp.data.model.MetamonUserManager
import com.google.gson.Gson
import dagger.BindsInstance
import dagger.Component
import com.dumihi.todoapp.core.Configs
import com.dumihi.todoapp.core.di.CoreComponent
import com.dumihi.todoapp.core.di.RemoteHost
import com.dumihi.todoapp.data.database.SellDbHelper
import com.dumihi.todoapp.data.database.SellDbManager
import com.dumihi.todoapp.data.repository.BuyRepository
import com.dumihi.todoapp.data.repository.CallRepository
import com.dumihi.todoapp.data.repository.SellRepository

@Component(
    modules = [
        NetworkModule::class,
        DataModule::class,
    ],
    dependencies = [CoreComponent::class]
)
@DataScoped
interface DataComponent {
    fun context(): Context

//    fun spaceRepository(): SpaceRepository

    fun connectionManager(): ConnectionManager //hieudm todo: check

    fun userManager(): MetamonUserManager

    @RemoteHost
    fun remoteHost(): String

//    @RemotePort
//    fun port(): Int?

    @PlatformCode
    fun platform(): String

    @AppVersion
    fun versions(): String

    @DatabaseNameInfo
    fun dbName(): String

    @DatabaseVersionInfo
    fun dbVersion(): Int

//    fun deviceInfo(): DeviceInfo

    fun sellDbHelper(): SellDbHelper

    fun sellDbManager(): SellDbManager

    fun configsData(): Configs

    fun callRepository(): CallRepository

    fun buyRepository(): BuyRepository

    fun sellRepository(): SellRepository

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance gson: Gson,
            coreComponent: CoreComponent,
            @BindsInstance userStorage: UserStorage,
            @BindsInstance @DatabaseNameInfo name: String,
            @BindsInstance @DatabaseVersionInfo version: Int
        ): DataComponent
    }
}