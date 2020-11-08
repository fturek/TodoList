package com.fturek.todolist.ui.listtodo.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import com.fturek.todolist.R
import com.fturek.todolist.data.model.TodoItem
import com.fturek.todolist.data.model.TodoItemResultDiffCallback
import javax.inject.Inject

class TodoListAdapter @Inject constructor() :
    PagedListAdapter<TodoItem, TodoItemViewHolder>(TodoItemResultDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_todo_item, parent, false)
        return TodoItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        val todoItem = getItem(position)
        holder.bind(
            todoItemTitle = todoItem?.title ?: "",
            todoItemDescription = todoItem?.description ?: "",
            todoItemUrl = todoItem?.iconUrl ?: "",
        )
    }

}