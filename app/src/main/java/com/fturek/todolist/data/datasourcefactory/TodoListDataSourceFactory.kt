package com.fturek.todolist.data.datasourcefactory

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.fturek.todolist.data.TodoDataSource
import com.fturek.todolist.data.model.TodoItem
import javax.inject.Inject

class TodoListDataSourceFactory @Inject constructor(
        var todoDataSource: TodoDataSource
) : DataSource.Factory<Int, TodoItem>() {

    var todoDataSourceLiveData: MutableLiveData<TodoDataSource>? = MutableLiveData<TodoDataSource>()

    override fun create(): DataSource<Int, TodoItem> {
        todoDataSourceLiveData?.postValue(todoDataSource)
        return todoDataSource
    }

}