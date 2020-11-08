package com.fturek.todolist.data.repository

import com.fturek.todolist.data.model.TodoItem
import com.google.firebase.firestore.CollectionReference
import io.reactivex.Single
import javax.inject.Inject

class TodoRepository @Inject constructor(
    var todoCollectionReference: CollectionReference
) {

    fun getTodoList(): Single<List<TodoItem>> {
        return Single.create<List<TodoItem>> { emitter ->
            todoCollectionReference
                .get()
                .addOnSuccessListener { querySnaphchot ->
                    val todoList = querySnaphchot
                        .map {
                            it.toObject(TodoItem::class.java)
                        }
                    emitter.onSuccess(todoList)
                }
                .addOnSuccessListener {

                }
        }
    }
}