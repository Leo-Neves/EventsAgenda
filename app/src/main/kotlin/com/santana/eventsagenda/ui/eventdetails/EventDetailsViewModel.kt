package com.santana.eventsagenda.ui.eventdetails

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.santana.eventsagenda.domain.model.CheckinBO
import com.santana.eventsagenda.domain.model.EventBO
import com.santana.eventsagenda.domain.usecase.CheckinUseCase
import com.santana.eventsagenda.domain.usecase.FetchEventDetailsUseCase
import com.santana.eventsagenda.state.EventResponse
import com.santana.eventsagenda.state.mapErrorToState
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

class EventDetailsViewModel @ViewModelInject constructor(
    private val fetchEventDetailsUseCase: FetchEventDetailsUseCase,
    private val checkinUseCase: CheckinUseCase,
    private val scheduler: Scheduler
): ViewModel(){

    private val _eventLiveData = MutableLiveData<EventResponse<EventBO>>()
    val eventLiveData get(): LiveData<EventResponse<EventBO>> = _eventLiveData
    private val _checkinLiveData = MutableLiveData<EventResponse<CheckinBO>>()
    val checkinLiveData get(): LiveData<EventResponse<CheckinBO>> = _checkinLiveData
    private val disposables = CompositeDisposable()
    private lateinit var eventId: String

    fun selectEvent(eventId: String) {
        this.eventId = eventId
    }

    fun fetchEventDetails() {
        val disposable = fetchEventDetailsUseCase
            .execute(FetchEventDetailsUseCase.Params(eventId))
            .subscribeOn(scheduler)
            .doOnSubscribe {
                _eventLiveData.postValue(EventResponse.EventLoading())
            }
            .subscribe({ event ->
                _eventLiveData.postValue(EventResponse.EventSuccess(event))
            }, { error ->
                _eventLiveData.postValue(mapErrorToState(error))
            })
        disposables.add(disposable)
    }

    fun checkinEvent(userName: String, userEmail: String){
        val disposable = checkinUseCase
            .execute(CheckinUseCase.Params(eventId, userEmail, userName))
            .subscribeOn(scheduler)
            .doOnSubscribe{
                _checkinLiveData.postValue(EventResponse.EventLoading())
            }
            .subscribe({checkin ->
                _checkinLiveData.postValue(EventResponse.EventSuccess(checkin))
            },{error ->
                _eventLiveData.postValue(mapErrorToState(error))
            })
        disposables.add(disposable)
    }

    override fun onCleared() {
        disposables.clear()
        super.onCleared ();
    }
}