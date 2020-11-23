package com.santana.eventsagenda.ui.eventlist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.santana.eventsagenda.R
import com.santana.eventsagenda.databinding.ActivityListBinding
import com.santana.eventsagenda.domain.model.EventBO
import com.santana.eventsagenda.state.EventResponse
import com.santana.eventsagenda.state.EventResponse.*
import com.santana.eventsagenda.ui.EventsRouter
import com.santana.eventsagenda.ui.EventsRouter.EVENTS_REFRESH_REQUEST_CODE
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EventsActivity : AppCompatActivity() {

    private val viewModel: EventsViewModel by viewModels()

    private val binding by lazy { ActivityListBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViews()
        setupObserver()
    }

    private fun hideProgressBar() {
        binding.swipeLayout.isRefreshing = false
    }

    private fun showProgressBar() {
        binding.swipeLayout.isRefreshing = true
    }

    private fun setupViews() {
        setupRecyclerView()
        setupToolbar()
        viewModel.listEvents()
    }

    private fun setupRecyclerView() {
        binding.rvUsers.setHasFixedSize(true)
        binding.rvUsers.layoutManager = LinearLayoutManager(this)
    }

    private fun setupToolbar() {
        binding.toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.itemRefresh -> viewModel.listEvents()
            }
            true
        }
    }

    private fun showEventsList(events: List<EventBO>) {
        binding.rvUsers.adapter = EventsAdapter(this, events) { event ->
            EventsRouter.openEventDatailsActivity(this, event.id)
        }
    }

    private fun setupObserver() {
        val observer = Observer<EventResponse<List<EventBO>>> { state ->
            when (state) {
                is EventLoading -> showProgressBar()
                is EventSuccess -> {
                    showEventsList(state.data)
                    hideProgressBar()
                }
                is NetworkError -> {
                    Toast.makeText(this, getString(R.string.error_network), LENGTH_LONG).show()
                    hideProgressBar()
                }
                is GenericError -> {
                    Toast.makeText(this, getString(R.string.error_network), LENGTH_LONG).show()
                    hideProgressBar()
                }
            }
        }
        viewModel.eventsLiveData.observe(this, observer)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.let {
            val camesFromEventDetails = requestCode == EVENTS_REFRESH_REQUEST_CODE
            val needsRefresh = it.getBooleanExtra(EventsRouter.EVENTS_NEEDS_REFRESH, false)
            if (camesFromEventDetails && needsRefresh) {
                viewModel.listEvents()
            }
        }
    }
}