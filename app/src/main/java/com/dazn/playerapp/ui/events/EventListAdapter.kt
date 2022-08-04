package com.dazn.playerapp.ui.events

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dazn.playerapp.model.Event
import com.dazn.playerapp.R
import com.dazn.playerapp.extenstions.*

class EventListAdapter(private var listener: OnItemClickListener?): ListAdapter<Event, EventListAdapter.EventsViewHolder>(
    object : DiffUtil.ItemCallback<Event>() {
        override fun areItemsTheSame(oldItem: Event, newItem: Event) = true
        override fun areContentsTheSame(oldItem: Event, newItem: Event) = oldItem == newItem
    }
) {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): EventsViewHolder {
        return EventsViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.event_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(getItem(position), listener)
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