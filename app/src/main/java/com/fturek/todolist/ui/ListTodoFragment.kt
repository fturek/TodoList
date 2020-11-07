package com.fturek.todolist.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fturek.todolist.BaseApplication
import com.fturek.todolist.databinding.FragmentListTodoBinding

class ListTodoFragment : Fragment() {

    private var _binding: FragmentListTodoBinding? = null
    private val binding get() = _binding!!

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