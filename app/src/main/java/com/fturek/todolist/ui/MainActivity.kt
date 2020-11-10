package com.fturek.todolist.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.fturek.todolist.BaseApplication
import com.fturek.todolist.R
import com.fturek.todolist.databinding.ActivityMainBinding
import com.fturek.todolist.ui.listtodo.ListTodoFragment

class MainActivity : BaseActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun inject() {
        (application as BaseApplication).appComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    companion object {
        fun intentFor(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }
}