package com.fturek.todolist.ui.listtodo.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.fturek.todolist.databinding.ViewTodoItemBinding
import com.fturek.todolist.ui.utils.DateUtils
import com.google.firebase.Timestamp
import kotlinx.android.extensions.LayoutContainer

class TodoItemViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private var binding = ViewTodoItemBinding.bind(itemView)

    fun bind(
        todoItemTitle: String,
        todoItemDescription: String,
        todoItemUrl: String,
        createdAt: Timestamp?
    ) {
        setTitle(todoItemTitle)
        setDescription(todoItemDescription)
        setIcon(todoItemUrl)
        setCreatedAtDate(createdAt)
    }

    private fun setTitle(todoItemTitle: String) {
        binding.todoItemTitle.text = todoItemTitle
    }

    private fun setDescription(todoItemDescription: String) {
        binding.todoItemDescription.text = todoItemDescription
    }

    private fun setIcon(todoItemUrl: String) {
        Glide
            .with(itemView.context)
            .load(todoItemUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .centerInside()
            .into(binding.todoItemIcon)
    }

    private fun setCreatedAtDate(createdAt: Timestamp?) {
        binding.todoCreatedAt.text = DateUtils.getDateInFormat(createdAt?.toDate())
    }

}