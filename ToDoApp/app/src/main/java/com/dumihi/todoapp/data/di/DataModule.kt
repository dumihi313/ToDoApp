package com.dumihi.todoapp.data.di

import com.dumihi.todoapp.app.utils.AppConnectionManager
import com.dumihi.todoapp.app.utils.ConnectionManager
import com.dumihi.todoapp.data.database.SellDbManager
import com.dumihi.todoapp.data.local.UserStorage
import com.dumihi.todoapp.data.model.MetamonUserManager
import com.dumihi.todoapp.data.repository.*
import com.dumihi.todoapp.data.restapi.BuyListApi
import com.dumihi.todoapp.data.restapi.CallListApi
import com.dumihi.todoapp.data.service.BuyService
import com.dumihi.todoapp.data.service.BuyServiceImpl
import com.dumihi.todoapp.data.service.CallService
import com.dumihi.todoapp.data.service.CallServiceImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class DataModule {

    //dependency
//    @Provides
//    @DatabaseNameInfo
//    fun provideDBName(): String = SellReaderContract.SellEntry.TABLE_NAME
//
//    @Provides
//    @DatabaseVersionInfo
//    fun provideDBVersion(): Int = SellDbHelper.DATABASE_VERSION

    @Provides
    fun provideConnectionManager(): ConnectionManager =
        AppConnectionManager()

    @Provides
    fun provideUserManager(localStorage: UserStorage): MetamonUserManager {
        return MetamonUserManager.getInstance(localStorage)
    }

    @Provides
    fun provideCallListApi(retroFit: Retrofit): CallListApi {
        return retroFit.create(CallListApi::class.java)
    }

    @Provides
    fun providesCallRepository(service: CallService): CallRepository = CallRepositoryImpl(service)

    @Provides
    fun providesCallService(callListApi: CallListApi): CallService = CallServiceImpl(callListApi)

    @Provides
    fun provideBuyListApi(retroFit: Retrofit): BuyListApi {
        return retroFit.create(BuyListApi::class.java)
    }

    @Provides
    fun providesBuyRepository(service: BuyService): BuyRepository = BuyRepositoryImpl(service)

    @Provides
    fun providesBuyService(api: BuyListApi): BuyService = BuyServiceImpl(api)

    @Provides
    fun providesSellRepository(sellDbManager: SellDbManager): SellRepository =
        SellRepositoryImpl(sellDbManager)
}