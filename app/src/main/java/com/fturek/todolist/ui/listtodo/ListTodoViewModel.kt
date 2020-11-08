package com.fturek.todolist.ui.listtodo

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.fturek.todolist.data.datasourcefactory.TodoListDataSourceFactory
import com.fturek.todolist.data.model.TodoItem
import javax.inject.Inject

class ListTodoViewModel @Inject constructor(
    var todoListDataSourceFactory: TodoListDataSourceFactory
) : ViewModel() {

    private var todoList: LiveData<PagedList<TodoItem>>? = null

    @SuppressLint("CheckResult")
    fun getTodos(): LiveData<PagedList<TodoItem>>? {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setInitialLoadSizeHint(20)
            .setEnablePlaceholders(false)
            .build()

        todoList = LivePagedListBuilder(todoListDataSourceFactory, config)
            .setInitialLoadKey(1)
            .build()

        return todoList
    }
}