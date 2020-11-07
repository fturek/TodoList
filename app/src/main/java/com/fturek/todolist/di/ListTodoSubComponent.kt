package com.fturek.todolist.di

import com.fturek.todolist.ui.ListTodoFragment
import dagger.Subcomponent
import javax.inject.Singleton

@Singleton
@Subcomponent(
    modules = [
        ListTodoModule::class
    ]
)
interface ListTodoSubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): ListTodoSubComponent
    }

    fun inject(fragment: ListTodoFragment)
}