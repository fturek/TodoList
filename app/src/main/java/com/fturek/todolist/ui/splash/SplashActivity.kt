package com.fturek.todolist.ui.splash

import android.os.Bundle
import android.os.Handler
import com.fturek.todolist.databinding.ActivitySplashBinding
import com.fturek.todolist.ui.BaseActivity
import com.fturek.todolist.ui.MainActivity

class SplashActivity : BaseActivity() {

    private val SPLASH_SCREEN_TIME_OUT = 2000L

    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding!!

    override fun inject() {
        // Do nothing
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler()
            .postDelayed(
                {
                    startActivity(
                        MainActivity.intentFor(this)
                    )

                    finish()
                }, SPLASH_SCREEN_TIME_OUT
            )
    }
}