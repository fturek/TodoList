package com.fturek.todolist.ui.listtodo.list

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.fturek.todolist.databinding.ViewTodoItemBinding
import kotlinx.android.extensions.LayoutContainer

class TodoItemViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    private var binding = ViewTodoItemBinding.bind(itemView)

    fun bind(todoItemTitle: String, todoItemDescription: String, todoItemUrl: String) {
        setTitle(todoItemTitle)
        setDescription(todoItemDescription)
        setIcon(todoItemUrl)
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

}