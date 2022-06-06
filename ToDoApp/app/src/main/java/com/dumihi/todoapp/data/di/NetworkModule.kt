package com.dumihi.todoapp.data.di

import android.content.Context
import android.util.Log
import com.dumihi.todoapp.BuildConfig
import com.dumihi.todoapp.core.di.AppVersion
import com.dumihi.todoapp.core.di.PlatformCode
import com.dumihi.todoapp.core.di.RemoteHost
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

private const val REST_TAG = "RestClient"

@Module
class NetworkModule {

    private fun createLoggingInterceptor(): HttpLoggingInterceptor? {
        if (!BuildConfig.DEBUG) {
            return null
        }
        val httpLoggingInterceptorLogger = HttpLoggingInterceptor.Logger { message ->
            if (BuildConfig.DEBUG) {
                Log.d(
                    REST_TAG,
                    message.replace(
                        "%".toRegex(),
                        "%%"
                    )
                )
            }
        }

        val httpLoggingInterceptor = HttpLoggingInterceptor(httpLoggingInterceptorLogger)

        httpLoggingInterceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        return httpLoggingInterceptor
    }

    @Provides
    @DataScoped
    fun provideCache(context: Context): Cache {
        return Cache(context.applicationContext.cacheDir, 10 * 1024 * 1024L)
    }

    @Provides
    @DataScoped
    fun provideRetrofitInterface(
        cache: Cache,
        @RemoteHost host: String,
        @PlatformCode platformCode: String,
        @AppVersion appVersion: String,
        gson: Gson = GsonBuilder().create(),
    ): Retrofit {
        val okHttpBuilder = OkHttpClient.Builder()

        okHttpBuilder.cache(cache)

        okHttpBuilder.addInterceptor {
            //add request interceptor
            val request = it.request().newBuilder()
                .addHeader("locale", Locale.getDefault().language)
                .build()
            val url = request.url().newBuilder()
//                .addQueryParameter("appversion", appVersion)
//                .addQueryParameter("platform", platformCode.toString())
                .build()
            it.proceed(request.newBuilder().url(url).build())
        }

        createLoggingInterceptor()?.let {
            okHttpBuilder.addInterceptor(it)
        }

        okHttpBuilder.readTimeout(60, TimeUnit.SECONDS)
        okHttpBuilder.writeTimeout(60, TimeUnit.SECONDS)
        okHttpBuilder.connectTimeout(60, TimeUnit.SECONDS)
        return Retrofit.Builder()
            .baseUrl(host)
            .client(okHttpBuilder.build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}