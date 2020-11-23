package com.santana.eventsagenda.state

sealed class StateResponse<T>() {
    class StateLoading<T>: StateResponse<T>()
    class StateSuccess<T>(val data: T): StateResponse<T>()
    class DataNotFound<T>(val data: T? = null): StateResponse<T>()
    class NetworkError<T>(val error: Throwable): StateResponse<T>()
    class GenericError<T>(val error: Throwable): StateResponse<T>()
}