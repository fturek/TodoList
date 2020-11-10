package com.fturek.todolist.ui.listtodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.fturek.todolist.BaseApplication
import com.fturek.todolist.databinding.FragmentAddEditTodoBinding
import com.fturek.todolist.di.viewmodels.ViewModelProviderFactory
import com.fturek.todolist.ui.BaseFragment
import javax.inject.Inject

class AddEditTodoFragment : BaseFragment() {

    private var _binding: FragmentAddEditTodoBinding? = null
    private val binding get() = _binding!!

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


    }

}