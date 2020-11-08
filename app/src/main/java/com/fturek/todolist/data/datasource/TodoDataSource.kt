package com.fturek.todolist.data.datasource

import android.annotation.SuppressLint
import androidx.paging.PageKeyedDataSource
import com.fturek.todolist.data.model.TodoItem
import com.fturek.todolist.data.repository.TodoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TodoDataSource @Inject constructor(
    var todoRepository: TodoRepository
) : PageKeyedDataSource<Int, TodoItem>() {

    private var pagesToLoad = 0

    @SuppressLint("CheckResult")
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, TodoItem>
    ) {

        todoRepository
            .getTodoList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { todoList ->

                    callback.onResult(todoList, null, 2)
                },
                { throwable ->
                    if (throwable == null) {
                        return@subscribe
                    }
                    // TODO handle
                }
            )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, TodoItem>) {}

    @SuppressLint("CheckResult")
    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, TodoItem?>) {

    }
}