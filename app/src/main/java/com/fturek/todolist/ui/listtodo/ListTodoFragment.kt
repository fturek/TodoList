package com.fturek.todolist.ui.listtodo

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.fturek.todolist.BaseApplication
import com.fturek.todolist.R
import com.fturek.todolist.data.Status
import com.fturek.todolist.data.model.TodoItem
import com.fturek.todolist.databinding.FragmentListTodoBinding
import com.fturek.todolist.di.viewmodels.ViewModelProviderFactory
import com.fturek.todolist.ui.BaseFragment
import com.fturek.todolist.ui.listtodo.AddEditTodoFragment.Companion.TODO_ACTION_EXTRA
import com.fturek.todolist.ui.listtodo.AddEditTodoFragment.Companion.TODO_ITEM_EXTRA
import com.fturek.todolist.ui.listtodo.list.TodoListAdapter
import javax.inject.Inject

class ListTodoFragment : BaseFragment() {

    private var _binding: FragmentListTodoBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    @Inject
    lateinit var todosListAdapter: TodoListAdapter

    private var navController: NavController? = null

    private val viewModel: ListTodoViewModel by lazy {
        ViewModelProvider(requireActivity(), viewModelFactory).get(ListTodoViewModel::class.java)
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

        navController = Navigation.findNavController(view)

        binding.todoRecyclerView.adapter = todosListAdapter
        binding.todoRecyclerView.layoutManager = LinearLayoutManager(activity)

        handleShowingPlaceHolder()
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
                handleShowingPlaceHolder()
            })
    }

    @SuppressLint("CheckResult")
    private fun handleClicks() {
        (binding.todoRecyclerView.adapter as TodoListAdapter)
            .clickSubject
            .subscribe { todoItem ->

                val bundle = bundleOf(
                    TODO_ACTION_EXTRA to TODO_ACTION_EDIT,
                    TODO_ITEM_EXTRA to todoItem
                )
                if (navController?.currentDestination?.id != R.id.listTodoFragment) {
                    return@subscribe
                }
                navController?.navigate(
                    R.id.action_listTodoFragment_to_addEditTodoFragment,
                    bundle
                )
            }

        (binding.todoRecyclerView.adapter as TodoListAdapter)
            .longClickSubject
            .subscribe { todoItem ->
                showDeleteDialog(todoItem)
            }

        binding.fab.setOnClickListener {
            val bundle = bundleOf(
                TODO_ACTION_EXTRA to TODO_ACTION_NEW,
            )
            navController?.navigate(
                R.id.action_listTodoFragment_to_addEditTodoFragment,
                bundle
            )
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

    // TODO can be moved to BaseFragment
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
                        handleShowingPlaceHolder()
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
                    Status.ADDED -> {
                        showToastWithMsg(
                            getString(R.string.add_item_success)
                        )
                    }
                    Status.ADDED_FAILED -> {
                        showToastWithMsg(
                            getString(R.string.add_item_error)
                        )
                    }
                    Status.UPDATED -> {
                        showToastWithMsg(
                            getString(R.string.update_item_success)
                        )
                    }
                    Status.UPDATED_FAILED -> {
                        showToastWithMsg(
                            getString(R.string.update_item_error)
                        )
                    }
                }
            })
    }

    private fun handleShowingPlaceHolder() {
        if (!(binding.todoRecyclerView.adapter as TodoListAdapter).todoList.isNullOrEmpty()) {
            binding.emptyPlaceHolder.visibility = View.GONE
            return
        }
        binding.emptyPlaceHolder.visibility = View.VISIBLE
    }

    companion object {
        const val TODO_ACTION_NEW = "new"
        const val TODO_ACTION_EDIT = "edit"
    }
}