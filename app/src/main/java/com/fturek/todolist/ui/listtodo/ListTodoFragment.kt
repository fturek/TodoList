package com.fturek.todolist.ui.listtodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fturek.todolist.BaseApplication
import com.fturek.todolist.databinding.FragmentListTodoBinding
import com.fturek.todolist.di.viewmodels.ViewModelProviderFactory
import com.fturek.todolist.ui.BaseFragment
import com.fturek.todolist.ui.listtodo.list.TodoListAdapter
import javax.inject.Inject

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
    }

    override fun onStart() {
        super.onStart()

        viewModel
            .getTodos()
            ?.observe(viewLifecycleOwner, {
                (binding.todoRecyclerView.adapter as TodoListAdapter).submitList(it)
                (binding.todoRecyclerView.adapter as TodoListAdapter).notifyDataSetChanged()
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