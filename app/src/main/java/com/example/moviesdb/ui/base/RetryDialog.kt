package com.example.moviesdb.ui.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.moviesdb.databinding.RetryDialogBinding
import kotlinx.android.synthetic.main.retry_dialog.*

class RetryDialog : BaseDialog() {


    interface onRetryListener {
        fun onRetry()
    }

    companion object {
        fun newInstance(): RetryDialog {
            val args = Bundle()
            val fragment = RetryDialog()
            fragment.arguments = args
            return fragment
        }
    }

    private var retryListener: onRetryListener? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            retryListener = parentFragment as onRetryListener
        } catch (e: ClassCastException) {
            throw ClassCastException("Calling fragment must implement Callback interface")
        }
    }

    override fun onDetach() {
        super.onDetach()
        retryListener = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = RetryDialogBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewClicked() {
        btn_retry.setOnClickListener {
            dismiss()
            retryListener!!.onRetry()
        }
    }

    override fun init() {
    }

}