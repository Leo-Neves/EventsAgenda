package com.santana.eventsagenda.ui.eventdetails

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.santana.eventsagenda.databinding.ActivityDetailsBinding
import com.santana.eventsagenda.R
import com.santana.eventsagenda.domain.model.CheckinBO
import com.santana.eventsagenda.domain.model.EventBO
import com.santana.eventsagenda.state.EventResponse
import com.santana.eventsagenda.utils.setVisibility
import com.santana.eventsagenda.ui.EventsRouter.EVENT_SELECTED_ID
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.concurrent.schedule

@AndroidEntryPoint
class EventDetailsActivity : AppCompatActivity() {

    private val viewModel: EventDetailsViewModel by viewModels()
    private val eventId by lazy { intent.getStringExtra(EVENT_SELECTED_ID) }

    private val binding by lazy { ActivityDetailsBinding.inflate(layoutInflater) }
    private lateinit var dialogCheckin: Dialog
    private var dialogLoading: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupViews()
        setupEventObserver()
        setupCheckinObserver()
        viewModel.fetchEventDetails()
    }

    private fun setupViews() {
        showLoading()
        openCheckinDialog()
    }

    private fun openCheckinDialog() {
        binding.btnCheckin.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.dialog_checkin, null, false)
            val etName = view.findViewById<EditText>(R.id.etName)
            val etEmail = view.findViewById<EditText>(R.id.etEmail)
            val btnConfirm = view.findViewById<Button>(R.id.btnConfirmCheckin)
            dialogCheckin = Dialog(this)
            dialogCheckin.setContentView(view)
            btnConfirm.setOnClickListener{
                if (etName.text.isNotEmpty() && etEmail.text.isNotEmpty()){
                    viewModel.checkinEvent(etName?.text.toString(), etEmail?.text.toString())
                }else{
                    if (etName.text.isEmpty()) etName.error = getString(R.string.fill_field)
                    if (etEmail.text.isEmpty()) etEmail.error = getString(R.string.fill_field)
                }
            }
            dialogCheckin.show()
        }
    }

    private fun showLoading() {
        if (dialogLoading==null || !dialogLoading!!.isShowing) {
            dialogLoading = Dialog(this)
            (dialogLoading as Dialog).setContentView(R.layout.dialog_loading)
            (dialogLoading as Dialog).setCancelable(false)
            (dialogLoading as Dialog).show()
        }
    }

    private fun hideLoading() {
        Timer().schedule(1000){
            dialogLoading?.dismiss()
        }
    }

    private fun setupEventObserver() {
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

    private fun setupCheckinObserver() {
        val observer = Observer<EventResponse<CheckinBO>> { state ->
            when (state) {
                is EventResponse.EventLoading -> showLoading()
                is EventResponse.EventSuccess -> {
                    dialogCheckin.dismiss()
                    hideLoading()
                    showCheckinSuccessMessage()
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
        viewModel.checkinLiveData.observe(this, observer)
    }

    private fun showCheckinSuccessMessage() {
        Toast.makeText(this, R.string.checkin_done, Toast.LENGTH_LONG).show()
    }

    private fun populateEventDetails(eventDetails: EventBO) {
        //TODO fill fields with event data
    }

    private fun showErrorMessage() {
        Toast.makeText(this, R.string.error_generic, Toast.LENGTH_LONG).show()
    }
}