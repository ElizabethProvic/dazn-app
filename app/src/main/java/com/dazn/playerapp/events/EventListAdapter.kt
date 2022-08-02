package com.dazn.playerapp.events.ui

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dazn.playerapp.model.Event
import com.dazn.playerapp.R
import com.dazn.playerapp.extenstions.*

class EventListAdapter(var events: ArrayList<Event>, private var listener: OnItemClickListener?): RecyclerView.Adapter<EventListAdapter.EventsViewHolder>() {

    fun updateEvents(newEvents: ArrayList<Event>) {
        events.clear()
        events.addAll(newEvents)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EventsViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.event_card, parent, false)
    )

    override fun getItemCount() = events.size

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(events[position], listener)
    }

    interface OnItemClickListener {
        fun onContentItemClick(id: String)
    }

    class EventsViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val title: TextView = itemView.findViewById(R.id.title)
        private val subtitle: TextView = itemView.findViewById(R.id.subtitle)
        private val date: TextView = itemView.findViewById(R.id.date)
        private val progressDrawable = getProgressDrawable(view.context)
        private val imageView: ImageView = itemView.findViewById(R.id.thumbnail)

        fun bind(event: Event, listener: OnItemClickListener?) {
            title.text = event.title
            subtitle.text = event.subtitle

            val startTime = event.date.time

            val kickOff = when {
                isDateTomorrow(startTime) -> "Tomorrow ${event.date.eventTimeFormatToString()}"
                DateUtils.isToday(startTime) -> "Today ${event.date.eventTimeFormatToString()}"
                else -> event.date.eventDateFormatToString()
            }

            date.text = kickOff

            imageView.loadImage(event.imageUrl, progressDrawable)

            itemView.setOnClickListener {
                event.videoUrl?.let { url -> listener?.onContentItemClick(url) }
            }
        }
    }
}