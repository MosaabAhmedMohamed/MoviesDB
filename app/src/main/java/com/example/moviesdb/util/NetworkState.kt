package com.example.moviesdb.util

class NetworkState constructor(val viewStatus: ViewStatus,val message: String) {

    companion object {
        var LOADING: NetworkState = NetworkState(ViewStatus.RUNNING, "running")
        var LOADED: NetworkState = NetworkState(ViewStatus.SUCCESS, "success")
        var UNAUTHORIZED: NetworkState = NetworkState(ViewStatus.UNAUTHORIZED, "unauthorized")

        fun error(message: String): NetworkState? {
            return NetworkState(ViewStatus.FAILED, message)
        }
    }


}

enum class ViewStatus {
    RUNNING,
    SUCCESS,
    FAILED,
    UNAUTHORIZED
}
