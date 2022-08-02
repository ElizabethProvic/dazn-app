package com.dazn.playerapp.schedule.ui

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dazn.playerapp.R
import com.dazn.playerapp.extenstions.*
import com.dazn.playerapp.model.ScheduleItem

class ScheduleListAdapter(var scheduleList: ArrayList<ScheduleItem>): RecyclerView.Adapter<ScheduleListAdapter.EventsViewHolder>() {

    fun updateSchedule(newEvents: ArrayList<ScheduleItem>) {
        scheduleList.clear()
        scheduleList.addAll(newEvents)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = EventsViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.event_card, parent, false)
    )

    override fun getItemCount() = scheduleList.size

    override fun onBindViewHolder(holder: EventsViewHolder, position: Int) {
        holder.bind(scheduleList[position])
    }

    class EventsViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val title: TextView = itemView.findViewById(R.id.title)
        private val subtitle: TextView = itemView.findViewById(R.id.subtitle)
        private val date: TextView = itemView.findViewById(R.id.date)
        private val progressDrawable = getProgressDrawable(view.context)
        private val imageView: ImageView = itemView.findViewById(R.id.thumbnail)

        fun bind(event: ScheduleItem) {
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
        }
    }
}