package com.example.moviesdb.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.moviesdb.di.Injectable
import com.example.moviesdb.presentation.BaseViewState
import com.example.moviesdb.util.ViewStatus
import dagger.android.AndroidInjection
import timber.log.Timber
import javax.inject.Inject

abstract class BaseFragment constructor(
    @LayoutRes
    private val layoutRes: Int
) : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(activity)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(layoutRes, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    protected abstract fun init()
    protected abstract fun onViewClicked()

    open fun showLoading(visibility: Boolean) {
        activity?.let {
            if (visibility) (activity as BaseActivity).showLoading()
            else (activity as BaseActivity).hideLoading()
        }
    }

    open fun showRetryDialog(retry: Boolean) {
        if (retry) RetryDialog.newInstance()
            .show(childFragmentManager, RetryDialog::class.simpleName)
    }

    protected fun <T> renderViewState(viewState: BaseViewState<T>?) {
        if (viewState != null) {
            when (viewState.networkState?.viewStatus) {
                ViewStatus.RUNNING -> {
                    showLoading(true)
                }
                ViewStatus.SUCCESS -> {
                    showLoading(false)
                    viewState.response?.let { it -> renderData(it) }!!
                }
                ViewStatus.FAILED -> {
                    showLoading(false)
                    //makeToast(viewState.getNetworkState().getMessage())*/
                }
                ViewStatus.UNAUTHORIZED -> {
                    showLoading(false)
                    //goToLogin()
                }
            }
        }

    }

    protected open fun <T> renderData(model: T) {

    }

    open fun navigate(action: Int, args: Bundle? = null) {
        findNavController().navigate(action, args)
    }

}