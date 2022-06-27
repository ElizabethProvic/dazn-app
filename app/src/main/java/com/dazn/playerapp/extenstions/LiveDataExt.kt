package com.dazn.playerapp.extenstions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData

fun <T> LiveData<ViewData<T>>.observe(
    owner: LifecycleOwner,
    autoDismissLoading: Boolean = true,
    onNext: ((T) -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null,
    onLoading: ((Boolean) -> Unit)? = null,
    onRefreshing: ((Boolean) -> Unit)? = null
) {
    observe(owner, {
        if (it !is ViewData.Refreshing && it !is ViewData.Loading && autoDismissLoading) {
            onLoading?.invoke(false)
            onRefreshing?.invoke(false)
        }

        when (it) {
            is ViewData.Loading -> onLoading?.invoke(it.isLoading)
            is ViewData.Refreshing -> onRefreshing?.invoke(it.isRefreshing)
            is ViewData.Data -> onNext?.invoke(it.content)
            is ViewData.Error -> onError?.invoke(it.throwable)
        }
    })
}
