package com.fturek.todolist.ui.listtodo.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fturek.todolist.R
import com.fturek.todolist.data.model.TodoItem
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject
import javax.inject.Named

class TodoListAdapter @Inject constructor(
    @Named("clickSubject") var clickSubject: PublishSubject<TodoItem>,
    @Named("longClickSubject") var longClickSubject: PublishSubject<TodoItem>
) : RecyclerView.Adapter<TodoItemViewHolder>() {

    var todoList: List<TodoItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_todo_item, parent, false)
        return TodoItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        val todoItem = todoList[position]
        holder.bind(
            todoItemTitle = todoItem.title ?: "",
            todoItemDescription = todoItem.description ?: "",
            todoItemUrl = todoItem.iconUrl ?: "",
            createdAt = todoItem.createdAt
        )

        holder.itemView.setOnClickListener {
            clickSubject.onNext(todoItem)
        }

        holder.itemView.setOnLongClickListener {
            longClickSubject.onNext(todoItem)
            true
        }
    }

    override fun getItemCount() = todoList.size

}