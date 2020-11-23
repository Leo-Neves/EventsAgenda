package com.santana.eventsagenda.ui.eventdetails

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.santana.eventsagenda.databinding.ActivityDetailsBinding
import com.santana.eventsagenda.R
import com.santana.eventsagenda.domain.model.EventBO
import com.santana.eventsagenda.state.EventResponse
import com.santana.eventsagenda.utils.setVisibility
import com.santana.eventsagenda.ui.EventsRouter.EVENT_SELECTED_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventDetailsActivity : AppCompatActivity() {

    private val viewModel: EventDetailsViewModel by viewModels()
    private val eventId by lazy { intent.getStringExtra(EVENT_SELECTED_ID) }

    private val binding by lazy { ActivityDetailsBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViews()
        setupObserver()
        viewModel.fetchEventDetails()
    }

    private fun setupViews() {
        showLoading()
    }

    private fun showLoading() {
        binding.groupDetails.setVisibility(false)
        binding.pbDetails.setVisibility(true)
    }

    private fun hideLoading() {
        binding.groupDetails.setVisibility(true)
        binding.pbDetails.setVisibility(false)
    }

    private fun setupObserver() {
        val observer = Observer<EventResponse<EventBO>> { state ->
            when (state) {
                is EventResponse.EventLoading -> showLoading()
                is EventResponse.EventSuccess -> {
                    hideLoading()
                    populateEventDetails(state.data)
                }
                is EventResponse.GenericError -> {
                    hideLoading()
                    showErrorMessage()
                }
                is EventResponse.ServerError -> {
                    hideLoading()
                    showErrorMessage()
                }
                is EventResponse.NetworkError -> {
                    hideLoading()
                    showErrorMessage()
                }
            }
        }
        viewModel.eventLiveData.observe(this, observer)
        viewModel.selectEvent(eventId)
    }

    private fun populateEventDetails(eventDetails: EventBO) {
        //TODO fill fields with event data
    }

    private fun showErrorMessage() {
        Toast.makeText(this, R.string.error_generic, Toast.LENGTH_LONG).show()
    }
}