package com.fturek.todolist.ui.listtodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.fturek.todolist.BaseApplication
import com.fturek.todolist.R
import com.fturek.todolist.data.model.TodoItem
import com.fturek.todolist.databinding.FragmentAddEditTodoBinding
import com.fturek.todolist.di.viewmodels.ViewModelProviderFactory
import com.fturek.todolist.ui.BaseFragment
import com.fturek.todolist.ui.listtodo.ListTodoFragment.Companion.TODO_ACTION_EDIT
import com.fturek.todolist.ui.listtodo.ListTodoFragment.Companion.TODO_ACTION_NEW
import com.google.firebase.Timestamp
import java.util.*
import javax.inject.Inject

class AddEditTodoFragment : BaseFragment(), View.OnClickListener {

    private var _binding: FragmentAddEditTodoBinding? = null
    private val binding get() = _binding!!

    private var navController: NavController? = null

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    private val viewModel: ListTodoViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(ListTodoViewModel::class.java)
    }

    var action = TODO_ACTION_NEW

    private var todoItemToEdit: TodoItem? = null

    override fun fetchFromRemote() {
        // do nothing
    }

    override fun inject() {
        (context?.applicationContext as BaseApplication)
            .appComponent.inject(this)

        (context?.applicationContext as BaseApplication)
            .appComponent
            .listTodoComponent()
            .create()
            .inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddEditTodoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        binding.submitButton.setOnClickListener(this)

        if (arguments == null) {
            return
        }

        action = arguments?.getString(TODO_ACTION_EXTRA) ?: ""
        if (action == TODO_ACTION_NEW) {
            binding.titleFragment.text = getString(R.string.todo_item_new_title_label)
            return
        }
        binding.titleFragment.text = getString(R.string.todo_item_edit_title_label)

        todoItemToEdit = requireArguments().getParcelable(TODO_ITEM_EXTRA) ?: return

        binding.title.setText(todoItemToEdit?.title)
        binding.description.setText(todoItemToEdit?.description)
        binding.iconUrl.setText(todoItemToEdit?.iconUrl)
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.submitButton.id -> {
                when (action) {
                    TODO_ACTION_NEW -> {
                        viewModel
                            .addItem(
                                prepareTodoItemToSend()
                            )
                    }
                    TODO_ACTION_EDIT -> {
                        viewModel
                            .updateItem(
                                todoItemToEdit,
                                prepareTodoItemToSend()
                            )
                        todoItemToEdit = null
                    }
                }
                navController?.navigate(R.id.action_addEditTodoFragment_to_listTodoFragment)
            }
        }
    }

    private fun prepareTodoItemToSend(): TodoItem {
        val todoItemTitle = binding.title.text.toString()
        val todoItemDescription = binding.description.text.toString()
        val todoItemIconUrl = binding.iconUrl.text.toString()

        return TodoItem(
            title = todoItemTitle,
            description = todoItemDescription,
            iconUrl = todoItemIconUrl,
            createdAt = Timestamp(Calendar.getInstance().time)
        )
    }

    companion object {
        const val TODO_ACTION_EXTRA = "edit"
        const val TODO_ITEM_EXTRA = "TODO_ITEM_EXTRA"
    }

}