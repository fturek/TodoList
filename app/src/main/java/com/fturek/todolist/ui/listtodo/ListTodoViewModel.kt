package com.fturek.todolist.ui.listtodo

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fturek.todolist.data.NetworkState
import com.fturek.todolist.data.Status
import com.fturek.todolist.data.model.TodoItem
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import javax.inject.Inject

class ListTodoViewModel @Inject constructor(
    var todoCollectionReference: CollectionReference,
    var networkState: MutableLiveData<NetworkState>
) : ViewModel() {


    private var todoList: MutableLiveData<List<TodoItem>> = MutableLiveData()

    @SuppressLint("CheckResult")
    fun getTodos(): LiveData<List<TodoItem>>? {
        networkState.postValue(NetworkState(Status.LOADING))

        todoCollectionReference
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(
                    querySnaphchot: QuerySnapshot?,
                    error: FirebaseFirestoreException?
                ) {
                    if (error != null) {
                        networkState.postValue(
                            NetworkState(
                                status = Status.FAILED,
                                error = error.cause
                            )
                        )
                        return
                    }

                    if (querySnaphchot == null) {
                        networkState.postValue(
                            NetworkState(
                                status = Status.FAILED,
                            )
                        )
                        return
                    }

                    val listMapped =
                        querySnaphchot
                            .map {
                                it.toObject(TodoItem::class.java)
                            }

                    todoList.postValue(listMapped)
                    networkState.postValue(NetworkState(Status.LOADED))
                }
            })

        return todoList
    }
}