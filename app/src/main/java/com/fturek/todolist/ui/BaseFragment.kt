package com.fturek.todolist.ui

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

abstract class BaseFragment : Fragment() {

    abstract fun inject()

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

}