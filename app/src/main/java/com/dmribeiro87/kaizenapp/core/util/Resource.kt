package com.dmribeiro87.kaizenapp.core.util


sealed class Resource<out T>(
    open val data: T? = null,
    open val message: String? = null
) {

    class Loading<T> : Resource<T>()
    data class Error<T>(override val message: String?, override val data: T? = null) :
        Resource<T>(data, message)

    data class Success<T>(override val data: T?) : Resource<T>(data)

}