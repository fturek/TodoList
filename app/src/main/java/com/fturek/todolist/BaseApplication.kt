package com.fturek.todolist

import android.app.Application
import com.fturek.todolist.di.AppComponent
import com.fturek.todolist.di.DaggerAppComponent

class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent =
            DaggerAppComponent
                .builder()
                .application(this)
                .build()
    }
}