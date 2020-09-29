package com.example.moviesdb.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import com.example.moviesdb.R

abstract class BaseDialog : DialogFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogStyle)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (dialog != null && dialog!!.window != null && isAdded) {
            dialog!!.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        }
        init()
        onViewClicked()
    }

    protected abstract fun onViewClicked()

    protected abstract fun init()

    protected open fun allowForcing() {
        if (dialog != null && dialog!!.window != null && isAdded) {
            dialog!!.setCanceledOnTouchOutside(false)
            isCancelable = false
        }
    }

    open fun showLoading(visibility: Boolean) {
        activity?.let {
            if (visibility) (activity as BaseActivity).showLoading()
            else (activity as BaseActivity).hideLoading()
        }
    }

}