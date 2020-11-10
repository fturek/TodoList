package com.fturek.todolist.di.modules

import androidx.lifecycle.MutableLiveData
import com.fturek.todolist.data.NetworkState
import com.fturek.todolist.data.model.TodoItem
import com.fturek.todolist.di.scopes.ListTodoScope
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject
import javax.inject.Named

@Module
class ListTodoModule {

    @ListTodoScope
    @Provides
    fun provideNetworkState(): MutableLiveData<NetworkState> {
        return MutableLiveData<NetworkState>()
    }

    @ListTodoScope
    @Provides
    @Named("clickSubject")
    fun provideTodoClickSubject(): PublishSubject<TodoItem> {
        return PublishSubject.create()
    }

    @ListTodoScope
    @Provides
    @Named("longClickSubject")
    fun provideTodoLongClickSubject(): PublishSubject<TodoItem> {
        return PublishSubject.create()
    }
}