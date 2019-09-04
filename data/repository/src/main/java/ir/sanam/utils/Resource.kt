package ir.sanam.utils

import ir.sanam.model.ErrorModel

data class Resource<out T>(val status: Status, val data: T?, val errorModel : ErrorModel?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(data : T?, errorModel : ErrorModel): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                errorModel
            )
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }
    }

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}