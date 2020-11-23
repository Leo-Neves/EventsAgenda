package com.santana.eventsagenda.state

sealed class EventResponse<T>() {
    class EventLoading<T>: EventResponse<T>()
    class EventSuccess<T>(val data: T): EventResponse<T>()
    class NetworkError<T>(val error: Throwable): EventResponse<T>()
    class ServerError<T>(val error: Throwable): EventResponse<T>()
    class GenericError<T>(val error: Throwable): EventResponse<T>()
}