package com.fturek.todolist.data.model

import androidx.recyclerview.widget.DiffUtil
import com.google.firebase.Timestamp

data class TodoItem(
    var title: String? = null,
    var description: String? = null,
    var createdAt: Timestamp? = null,
    var iconUrl: String? = null
)

class TodoItemResultDiffCallback : DiffUtil.ItemCallback<TodoItem>() {

    override fun areItemsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: TodoItem, newItem: TodoItem): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.description == newItem.description &&
                oldItem.createdAt == newItem.createdAt &&
                oldItem.iconUrl == newItem.iconUrl
    }
}