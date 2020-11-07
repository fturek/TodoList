package com.fturek.todolist.ui.listtodo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fturek.todolist.BaseApplication
import com.fturek.todolist.databinding.FragmentListTodoBinding
import com.fturek.todolist.di.viewmodels.ViewModelProviderFactory
import javax.inject.Inject

class ListTodoFragment : Fragment() {

    private var _binding: FragmentListTodoBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelProviderFactory

    private val viewModel: ListTodoViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(ListTodoViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        (context.applicationContext as BaseApplication)
            .appComponent
            .listTodosComponent()
            .create()
            .inject(this)
        super.onAttach(context)
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

        viewModel.isWorking()
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