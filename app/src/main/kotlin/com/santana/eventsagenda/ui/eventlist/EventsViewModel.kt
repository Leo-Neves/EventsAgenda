package com.santana.eventsagenda.ui.eventlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santana.eventsagenda.domain.model.EventBO
import com.santana.eventsagenda.domain.usecase.FetchEventsUseCase
import com.santana.eventsagenda.state.EventResponse
import com.santana.eventsagenda.state.EventResponse.*
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException

class EventsViewModel @ViewModelInject constructor(
    private val listUseCase: FetchEventsUseCase,
    private val scheduler: Scheduler
) : ViewModel() {

    private val _eventsLiveData = MutableLiveData<EventResponse<List<EventBO>>>()
    val eventsLiveData get(): LiveData<EventResponse<List<EventBO>>> = _eventsLiveData
    private val disposables = CompositeDisposable()

    fun listEvents() {
        val disposable = listUseCase.execute()
            .subscribeOn(scheduler)
            .doOnSubscribe {
                _eventsLiveData.postValue(EventLoading())
            }.subscribe({
                _eventsLiveData.postValue(EventSuccess(it))
            }, {
                if (it is HttpException) {
                    _eventsLiveData.postValue(NetworkError(it))
                } else {
                    _eventsLiveData.postValue(GenericError(it))
                }
            })
        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared ();
    }

}