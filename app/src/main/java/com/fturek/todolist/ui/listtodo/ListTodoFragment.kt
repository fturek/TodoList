package com.fturek.todolist.ui.listtodo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fturek.todolist.BaseApplication
import com.fturek.todolist.data.Status
import com.fturek.todolist.databinding.FragmentListTodoBinding
import com.fturek.todolist.di.viewmodels.ViewModelProviderFactory
import com.fturek.todolist.ui.BaseFragment
import com.fturek.todolist.ui.listtodo.list.TodoListAdapter
import javax.inject.Inject
import android.util.Log
import com.afollestad.materialdialogs.MaterialDialog
import com.fturek.todolist.R
import com.fturek.todolist.data.model.TodoItem

class ListTodoFragment : BaseFragment() {

    private var _binding: FragmentListTodoBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    @Inject
    lateinit var todosListAdapter: TodoListAdapter

    private val viewModel: ListTodoViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(ListTodoViewModel::class.java)
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
        _binding = FragmentListTodoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.todoRecyclerView.adapter = todosListAdapter
        binding.todoRecyclerView.layoutManager = LinearLayoutManager(activity)

        handleLoader()

        handleClicks()
    }


    override fun onStart() {
        super.onStart()

        fetchFromRemote()
    }

    override fun fetchFromRemote() {
        viewModel
            .getTodos()
            ?.observe(viewLifecycleOwner, {
                (binding.todoRecyclerView.adapter as TodoListAdapter).todoList = it
                (binding.todoRecyclerView.adapter as TodoListAdapter).notifyDataSetChanged()
            })
    }

    @SuppressLint("CheckResult")
    private fun handleClicks() {
        (binding.todoRecyclerView.adapter as TodoListAdapter)
            .clickSubject
            .subscribe {
                Log.e("Felipe", "just click")
            }

        (binding.todoRecyclerView.adapter as TodoListAdapter)
            .longClickSubject
            .subscribe { todoItem ->
                showDeleteDialog(todoItem)
            }
    }

    private fun showDeleteDialog(todoItem: TodoItem) {
        MaterialDialog(requireContext())
            .message(R.string.dialog_delete_item_title)
            .show {
                positiveButton(R.string.dialog_delete_item_agree) {
                    viewModel
                        .deleteTodoItem(
                            itemToDelete = todoItem
                        )
                }
                negativeButton(R.string.delete_delete_item_cancel) {
                    dismiss()
                }
            }
    }

    private fun handleLoader() {
        viewModel
            .networkState
            .observe(viewLifecycleOwner, { networkState ->
                when (networkState.status) {
                    Status.LOADING -> {
                        showHUD()
                    }
                    Status.LOADED -> {
                        hideHUD()
                    }
                    Status.FAILED -> {
                        hideHUD()

                        handleError(networkState)
                    }
                    Status.DELETED -> {
                        showToastWithMsg(
                            getString(R.string.delete_delete_item_success)
                        )
                    }
                    Status.DELETED_FAILED -> {
                        showToastWithMsg(
                            getString(R.string.delete_delete_item_error)
                        )
                    }
                }
            })
    }

    companion object {
        fun newInstance(): ListTodoFragment {
            val fragment = ListTodoFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            return fragment
        }
    }
}