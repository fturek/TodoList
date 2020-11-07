package com.fturek.todolist.ui.listtodo

import androidx.lifecycle.ViewModel
import javax.inject.Inject
import android.util.Log

class ListTodoViewModel @Inject constructor(
) : ViewModel() {

    fun isWorking(){
        Log.e("Felipe", "View model works")
    }
}