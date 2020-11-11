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
    }

    override fun onClick(v: View) {
        when (v.id) {
            binding.submitButton.id -> {
                viewModel
                    .addItem(
                        prepareTodoItemToSend()
                    )
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

}