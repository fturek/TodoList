package com.fturek.todolist.data

import com.google.firebase.firestore.Query

class FireBaseCustomOptions {

    companion object {
        const val ORDER_BY = "createdAt"
        val ORDER_BY_DIRECTION = Query.Direction.DESCENDING
    }
}