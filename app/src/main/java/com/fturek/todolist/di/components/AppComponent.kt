package com.fturek.todolist.di.components

import android.app.Application
import com.fturek.todolist.di.modules.AppModule
import com.fturek.todolist.di.modules.SubcomponentsModule
import com.fturek.todolist.di.viewmodels.ViewModelFactoryModule
import com.fturek.todolist.di.modules.ListTodoViewModelModule
import com.fturek.todolist.ui.BaseActivity
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        AppModule::class,
        SubcomponentsModule::class,
        ViewModelFactoryModule::class,
        ListTodoViewModelModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun listTodosComponent(): ListTodoSubComponent.Factory

    fun inject(baseActivity: BaseActivity)
}