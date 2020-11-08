package com.fturek.todolist.data.model

import com.google.firebase.Timestamp

data class TodoItem(
    var title: String? = null,
    var description: String? = null,
    var createdAt: Timestamp? = null,
    var iconUrl: String? = null
)