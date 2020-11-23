package com.santana.eventsagenda.ui.eventlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.santana.eventsagenda.R
import com.santana.eventsagenda.domain.model.EventBO
import com.santana.eventsagenda.utils.toDayMonthYear
import kotlin.math.max

class EventsAdapter(
    private val activity: AppCompatActivity,
    private val events: List<EventBO>,
    private val eventClickListener: (event: EventBO) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return if (events.isEmpty()) {
            EmptyHolder(inflateAdapter(R.layout.row_empty_events, parent))
        } else {
            EventHolder(inflateAdapter(R.layout.row_event, parent))
        }
    }

    private fun inflateAdapter(layoutRes: Int, parent: ViewGroup): View {
        return LayoutInflater.from(activity).inflate(layoutRes, parent, false)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is EventHolder) {
            val event = events.get(position)
            holder.bindValues(event, eventClickListener)
        }
    }

    override fun getItemCount(): Int = max(events.size, 1)

    class EmptyHolder(itemView: View) : ViewHolder(itemView)

    class EventHolder(
        itemView: View,
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle),
        var tvDate: TextView = itemView.findViewById(R.id.tvDate),
        var ivImage: ImageView = itemView.findViewById(R.id.ivImage)
    ) : ViewHolder(itemView) {

        fun bindValues(
            event: EventBO,
            userClickListener: (event: EventBO) -> Unit
        ) {
            tvTitle.text = event.title
            tvDate.text = event.date.toDayMonthYear()
            itemView.setOnClickListener { userClickListener.invoke(event) }
        }
    }
}