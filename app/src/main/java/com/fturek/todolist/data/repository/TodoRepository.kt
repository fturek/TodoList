package com.fturek.todolist.data.repository

import androidx.lifecycle.LifecycleObserver
import com.fturek.todolist.data.model.TodoItem
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import io.reactivex.Single
import javax.inject.Inject

class TodoRepository @Inject constructor(
    var todoCollectionReference: CollectionReference
//    var lifeCycleOwner: LifecycleOwner
) : LifecycleObserver {

//    init {
//        lifeCycleOwner.lifecycle.addObserver(this)
//    }

//    var addSnapshotListener: ListenerRegistration? = null

    //    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun getTodoList(): Single<List<TodoItem>> {
        return Single.create { emitter ->
//            addSnapshotListener = todoCollectionReference
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

                        val todoList = querySnaphchot
                            .map {
                                it.toObject(TodoItem::class.java)
                            }
                        emitter.onSuccess(todoList)
                    }


                })
        }
    }
//
//    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
//    fun removeListener() {
//        addSnapshotListener?.remove()
//    }
}