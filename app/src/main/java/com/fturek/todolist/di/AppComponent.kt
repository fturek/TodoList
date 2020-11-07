package com.fturek.todolist.di

import android.app.Application
import com.fturek.todolist.ui.BaseActivity
import com.fturek.todolist.ui.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        AppModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(baseActivity: BaseActivity)
}