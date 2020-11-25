package com.santana.eventsagenda.state

import retrofit2.HttpException

fun <T> mapErrorToState(error: Throwable): EventResponse<T>{
    return if (error is HttpException){
        if (error.code()==400){
            EventResponse.ServerError(error)
        }else{
            EventResponse.NetworkError(error)
        }
    } else {
        EventResponse.GenericError(error)
    }
}