package com.fturek.todolist.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.fturek.todolist.R
import com.fturek.todolist.data.NetworkState
import com.google.android.material.snackbar.Snackbar
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseFragment : Fragment() {

    private var animationLayout: ConstraintLayout? = null

    abstract fun inject()

    override fun onAttach(context: Context) {
        inject()
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        animationLayout = requireActivity().findViewById(R.id.loader_animation_layout)
    }

    abstract fun fetchFromRemote()

    fun handleError(networkState: NetworkState) {
        val errorBody = when (networkState.error) {
            is SocketTimeoutException -> {
                getString(R.string.error_network_timeout)
            }
            is UnknownHostException -> {
                getString(R.string.error_network_connection)
            }
            else -> {
                getString(R.string.error_something_went_wrong)
            }
        }

        showSnackBar(errorBody)
    }

    fun showToastWithMsg(msg: String){
        Toast
            .makeText(
                requireContext(),
                msg,
                Toast.LENGTH_LONG
            ).show()
    }

    fun showHUD() {
        animationLayout?.clearAnimation()

        animationLayout?.animate()?.apply {
            interpolator = LinearInterpolator()
            duration = 300
            alpha(1f)
            startDelay = 0
            start()
        }
    }

    fun hideHUD() {
        animationLayout?.clearAnimation()

        animationLayout?.animate()?.apply {
            interpolator = LinearInterpolator()
            duration = 300
            alpha(0f)
            startDelay = 200
            start()
        }
    }

    private fun showSnackBar(errorBody: String) {
        Snackbar
            .make(requireView(), errorBody, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.snack_bar_error_try_again) {
                showHUD()
            }
            .show()
    }
}