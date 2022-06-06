package com.dumihi.todoapp.app

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.dumihi.todoapp.BuildConfig
import com.dumihi.todoapp.app.di.DaggerAppComponent
import com.dumihi.todoapp.app.di.appcontext.DaggerContextComponent
import com.dumihi.todoapp.app.local.UserLocalStorage
import com.dumihi.todoapp.app.utils.TodoLifeCycleCallback
import com.dumihi.todoapp.core.Environment
import com.dumihi.todoapp.core.di.DaggerCoreComponent
import com.dumihi.todoapp.data.database.SellDbHelper
import com.dumihi.todoapp.data.database.SellReaderContract
import com.dumihi.todoapp.data.di.DaggerDataComponent
import com.google.gson.GsonBuilder
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class TodoApplication : Application(), HasAndroidInjector {

    @Inject
    @Volatile
    @JvmField
    var androidInjector: DispatchingAndroidInjector<Any>? = null

    @Inject
    lateinit var activityLifecycleCallback: TodoLifeCycleCallback

    private lateinit var environment: Environment

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

//    private val eventListener = EventListener { _, _ ->
//        environment.rotate()
//        clearInjection()
//        injectIfNecessary()
//
//    }


    override fun onCreate() {
        super.onCreate()

        environment =
            Environment.getInstance(getSharedPreferences("metamon_config", Context.MODE_PRIVATE))

        injectIfNecessary()

        registerActivityLifecycleCallbacks(activityLifecycleCallback)


//        EventDispatcher.getInstance().addListener(Events.SWITCH_ENVIRONMENT, eventListener)

    }

    private fun applicationInjector(): AndroidInjector<TodoApplication> {
        val contextComponent = DaggerContextComponent.factory().create(this)

        val coreComponent = DaggerCoreComponent.factory().create(
            contextComponent,
            environment.current().apiHost,
            "android",
            BuildConfig.VERSION_NAME
        )

        val gson = GsonBuilder().apply {
            //todo: add more
        }.create()

        val dataComponent = DaggerDataComponent.factory()
            .create(
                gson,
                coreComponent,
                UserLocalStorage(getSharedPreferences("user_prefs", MODE_PRIVATE)),
                SellReaderContract.SellEntry.TABLE_NAME,
                SellDbHelper.DATABASE_VERSION
            )
        return DaggerAppComponent.factory().create(dataComponent)
    }

    private fun injectIfNecessary() {
        if (androidInjector == null) {
            synchronized(this) {
                if (androidInjector == null) {
                    val applicationInjector = applicationInjector()
                    applicationInjector.inject(this)
                    if (androidInjector == null) {
                        throw IllegalStateException(
                            "The AndroidInjector returned from applicationInjector() did not inject the "
                                    + "DaggerApplication"
                        )
                    }
                }
            }
        }
    }

    private fun clearInjection() {
        synchronized(this) {
            androidInjector = null
        }
    }

    override fun androidInjector(): AndroidInjector<Any> {
        injectIfNecessary()

        return androidInjector as AndroidInjector<Any>
    }

}