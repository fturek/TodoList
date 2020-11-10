package com.fturek.todolist.ui.listtodo

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fturek.todolist.data.model.TodoItem
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class ListTodoViewModel @Inject constructor(
    var todoCollectionReference: CollectionReference
) : ViewModel() {

    private var todoList: MutableLiveData<List<TodoItem>> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getTodos(): LiveData<List<TodoItem>>? {
        todoCollectionReference
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(
                    querySnaphchot: QuerySnapshot?,
                    error: FirebaseFirestoreException?
                ) {
                    if (error != null) {
                        // Handle error
                        return
                    }

                    if (querySnaphchot == null) {
                        // Handle error
                        return
                    }

                    val listMapped = querySnaphchot
                        .map {
                            it.toObject(TodoItem::class.java)
                        }
                    todoList.postValue(listMapped)
                }
            })

        return todoList
    }
}