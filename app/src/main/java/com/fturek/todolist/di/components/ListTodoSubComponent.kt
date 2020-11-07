package com.fturek.todolist.di.components

import com.fturek.todolist.di.modules.ListTodoModule
import com.fturek.todolist.ui.listtodo.ListTodoFragment
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