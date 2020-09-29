package com.example.moviesdb.presentation

import com.example.moviesdb.util.NetworkState

open class BaseViewState<R> {
    var networkState: NetworkState? = null
    var response: R? = null
}