package com.dazn.playerapp.extenstions

sealed class ViewData<T> {

    data class Loading<T>(val isLoading: Boolean = true) : ViewData<T>()

    data class Refreshing<T>(val isRefreshing: Boolean = true) : ViewData<T>()

    data class Data<T>(val content: T) : ViewData<T>()

    data class Error<T>(val throwable: Throwable) : ViewData<T>()
}
