package com.fturek.todolist.di.modules

import androidx.lifecycle.MutableLiveData
import com.fturek.todolist.data.NetworkState
import com.fturek.todolist.di.scopes.ListTodoScope
import dagger.Module
import dagger.Provides

@Module
class ListTodoModule {

    @ListTodoScope
    @Provides
    fun provideNetworkState(): MutableLiveData<NetworkState> {
        return MutableLiveData<NetworkState>()
    }
}PublishSubject.create<TodoItem>()