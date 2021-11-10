package com.marvel.talentomobile.app.data.remote

data class StatusRequest<out T>(val status: Status, val data: T?, val message: String?) {

    enum class Status {
        SUCCESS,
        ERROR,
        LOADING
    }

    companion object {
        fun <T> success(data: T): StatusRequest<T> {
            return StatusRequest(Status.SUCCESS, data, null)
        }

        fun <T> error(message: String, data: T? = null): StatusRequest<T> {
            return StatusRequest(Status.ERROR, data, message)
        }

        fun <T> loading(data: T? = null): StatusRequest<T> {
            return StatusRequest(Status.LOADING, data, null)
        }
    }
}