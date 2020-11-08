package com.fturek.todolist.ui.listtodo

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.ViewModel
import com.fturek.todolist.data.repository.TodoRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListTodoViewModel @Inject constructor(
    var repository: TodoRepository
) : ViewModel() {

    @SuppressLint("CheckResult")
    fun isWorking() {
        repository
            .getTodoList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    Log.e("Felipe", "todos List " + it.size)
                }, {

                })


    }
}