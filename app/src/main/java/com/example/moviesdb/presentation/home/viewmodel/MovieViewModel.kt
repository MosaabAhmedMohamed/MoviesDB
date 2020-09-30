package com.example.moviesdb.presentation.home.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.moviesdb.data.home.remote.model.MovieDetailModel
import com.example.moviesdb.domin.usecase.MovieUseCase
import com.example.moviesdb.presentation.BaseViewState
import com.example.moviesdb.util.NetworkState
import com.example.moviesdb.util.rx.AppSchedulerProvider
import io.reactivex.functions.Consumer
import okhttp3.ResponseBody
import javax.inject.Inject

class MovieViewModel
@Inject
constructor(
    private val useCase: MovieUseCase
) : BaseViewModel(
    AppSchedulerProvider.io(),
    AppSchedulerProvider.ui()
) {

    val movieVS: MutableLiveData<BaseViewState<MovieDetailModel>> = MutableLiveData()
    val fileVS: MutableLiveData<BaseViewState<ResponseBody>> = MutableLiveData()
    var imgUrl: String? = null

    fun movie(id: Int) {
        val viewState: BaseViewState<MovieDetailModel> = BaseViewState()
        viewState.networkState = NetworkState.LOADED
        execute(
            Consumer {
                viewState.networkState = NetworkState.LOADING
                movieVS.postValue(viewState)
            },
            Consumer {
                viewState.networkState = NetworkState.LOADED
                viewState.response = it
                movieVS.postValue(viewState)
            },
            Consumer { throwable ->
                viewState.networkState = getFailedNetworkState(throwable)
                movieVS.postValue(viewState)
            },
            useCase.getMovie(id).toFlowable()
        )
    }

    fun downloadFile() {
        val viewState: BaseViewState<ResponseBody> = BaseViewState()
        viewState.networkState = NetworkState.LOADED
        imgUrl?.let { useCase.downloadFile(it).toFlowable() }?.let {
            execute(
                Consumer {
                    viewState.networkState = NetworkState.LOADING
                    fileVS.postValue(viewState)
                },
                Consumer {
                    viewState.networkState = NetworkState.LOADED
                    viewState.response = it
                    fileVS.postValue(viewState)
                },
                Consumer { throwable ->
                    viewState.networkState = getFailedNetworkState(throwable)
                    fileVS.postValue(viewState)
                },
                it
            )
        }
    }


}

