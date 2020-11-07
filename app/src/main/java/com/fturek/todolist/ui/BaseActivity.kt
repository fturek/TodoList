package com.fturek.todolist.ui

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fturek.todolist.R

abstract class BaseActivity : AppCompatActivity() {

    abstract fun inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        setOrientation()
        hideActionBar()
    }

    private fun setOrientation() {
        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    private fun hideActionBar() {
        supportActionBar?.hide()
    }
}

