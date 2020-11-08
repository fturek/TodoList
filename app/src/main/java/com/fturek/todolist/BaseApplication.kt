package com.fturek.todolist

import android.app.Application
import com.fturek.todolist.di.components.AppComponent
import com.fturek.todolist.di.components.DaggerAppComponent
import com.google.firebase.FirebaseApp

class BaseApplication : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
        FirebaseApp.initializeApp(this)
    }

    private fun initAppComponent() {
        appComponent =
            DaggerAppComponent
                .builder()
                .application(this)
                .build()
    }
}