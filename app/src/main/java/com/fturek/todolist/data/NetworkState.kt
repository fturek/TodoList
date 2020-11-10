package com.fturek.todolist.data

class NetworkState constructor(
    var status: Status? = null,
    var message: String? = null,
    var error: Throwable? = null
)

enum class Status {
    LOADING,
    LOADED,
    FAILED
}