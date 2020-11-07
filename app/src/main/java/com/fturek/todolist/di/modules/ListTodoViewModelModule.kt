package com.fturek.todolist.di.modules

import androidx.lifecycle.ViewModel
import com.fturek.todolist.di.viewmodels.ViewModelKey
import com.fturek.todolist.ui.listtodo.ListTodoViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
public abstract class ListTodoViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListTodoViewModel::class)
    public abstract fun bindListTodoViewModel(listTodoViewModel: ListTodoViewModel): ViewModel
}