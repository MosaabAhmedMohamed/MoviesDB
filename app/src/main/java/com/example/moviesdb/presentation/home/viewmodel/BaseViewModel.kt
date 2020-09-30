package com.example.moviesdb.presentation.home.viewmodel

import android.annotation.SuppressLint
import androidx.core.util.Preconditions
import androidx.lifecycle.ViewModel
import com.example.moviesdb.util.Constants.Companion.UNAUTHORIZED
import com.example.moviesdb.util.NetworkState
import io.reactivex.Flowable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import org.reactivestreams.Subscription
import retrofit2.HttpException
import timber.log.Timber

open class BaseViewModel(
    private var subscribeOn: Scheduler?,
    private var observeOn: Scheduler?
) : ViewModel() {

    private var disposables: CompositeDisposable? = null


    @SuppressLint("RestrictedApi")
    fun <T> execute(
        loadingConsumer: Consumer<Subscription>,
        successConsumer: Consumer<T>,
        throwableConsumer: Consumer<Throwable>,
        useCase: Flowable<T>
    ) {
        Preconditions.checkNotNull(
            successConsumer
        )
        Preconditions.checkNotNull(
            throwableConsumer
        )
        val observable: Flowable<T> = useCase
            .doOnSubscribe(loadingConsumer)
            .subscribeOn(subscribeOn!!)
            .observeOn(observeOn)

        if (disposables == null) {
            disposables = CompositeDisposable()
        }
        addDisposable(observable.subscribe(successConsumer, throwableConsumer))


    }

    @SuppressLint("RestrictedApi")
    protected fun <T> executePager(
        successConsumer: Consumer<T>?,
        useCase: Flowable<T>
    ) {
        Preconditions.checkNotNull(successConsumer)
        val observable = useCase
            .subscribeOn(subscribeOn!!)
            .observeOn(observeOn)
        addDisposable(observable.subscribe(successConsumer))
    }

    private fun isUnAuthorized(throwable: Throwable?): Boolean {
        return throwable is HttpException && throwable.code() == UNAUTHORIZED
    }

    protected fun getFailedNetworkState(throwable: Throwable): NetworkState? {
        return if (isUnAuthorized(throwable)) {
            NetworkState.UNAUTHORIZED
        } else {
            NetworkState.error(throwable.message.toString())
        }
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    private fun dispose() {
        if (!disposables?.isDisposed!!) {
            disposables?.dispose()
        }
    }

    /**
     * Dispose from current [CompositeDisposable].
     */
    @SuppressLint("RestrictedApi")
    private fun addDisposable(disposable: Disposable) {
        Preconditions.checkNotNull(disposable)
        disposables?.let {
            disposables!!.add(disposable)
        }

    }

    override fun onCleared() {
        super.onCleared()
        Timber.v("das")
        dispose()
    }
}