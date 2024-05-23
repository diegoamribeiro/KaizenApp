package com.dmribeiro87.kaizenapp.gamesFeature.presentation.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dmribeiro87.kaizenapp.R
import com.dmribeiro87.kaizenapp.core.util.EventCountDownTimer
import com.dmribeiro87.kaizenapp.core.util.SportsCountDownTimer
import com.dmribeiro87.kaizenapp.core.util.formatTime
import com.dmribeiro87.kaizenapp.databinding.ItemEventBinding
import com.dmribeiro87.kaizenapp.gamesFeature.domain.model.Event


class EventsAdapter(private var events: List<Event>) :
    RecyclerView.Adapter<EventsAdapter.EventViewHolder>() {

    private var filteredEvents: List<Event> = events
    private var action: ((Event) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val binding = ItemEventBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        holder.bind(filteredEvents[position], holder.itemView.context) { event ->
            action?.invoke(event)
        }
    }

    override fun getItemCount(): Int = filteredEvents.size

    fun filterFavorites(showFavorites: Boolean) {
        filteredEvents = if (showFavorites) {
            events.filter { it.isFavorite }
        } else {
            events
        }
        notifyDataSetChanged()
    }

    fun setAction(action: (Event) -> Unit) {
        this.action = action
    }

    inner class EventViewHolder(private val binding: ItemEventBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var eventCountDownTimer: EventCountDownTimer? = null

        fun bind(event: Event, context: Context, action: (Event) -> Unit) {
            binding.apply {
                setupEventDetails(event, action)
                setupCountDownTimer(event, context)
                setupFavoriteButton(event, action)
            }
        }

        private fun ItemEventBinding.setupEventDetails(event: Event, action: (Event) -> Unit) {
            tvCompetitorOne.text = event.competitorOne
            tvCompetitorTwo.text = event.competitorTwo
            ivFavorite.setImageResource(
                if (event.isFavorite) R.drawable.ic_star_filled else R.drawable.ic_star_border
            )
            root.setOnClickListener {
                action(event)
            }
        }

        private fun ItemEventBinding.setupCountDownTimer(event: Event, context: Context) {
            val currentTimeInMillis = System.currentTimeMillis()
            val eventStartTimeInMillis = event.startTime * 1000
            val remainingTimeInMillis = eventStartTimeInMillis - currentTimeInMillis

            eventCountDownTimer?.cancel()
            if (remainingTimeInMillis > 0) {
                eventCountDownTimer = EventCountDownTimer(
                    SportsCountDownTimer(
                        startTimeInMillis = remainingTimeInMillis,
                        intervalInMillis = 1000,
                        onTick = { millisUntilFinished ->
                            tvEventStartTime.text = formatTime(millisUntilFinished / 1000)
                            tvEventStartTime.setTextColor(
                                ContextCompat.getColor(
                                    root.context,
                                    R.color.switch_green
                                )
                            )
                        },
                        onFinish = {
                            tvEventStartTime.text = context.getString(R.string.label_finished)
                            tvEventStartTime.setTextColor(
                                ContextCompat.getColor(
                                    root.context,
                                    R.color.gray
                                )
                            )
                        }
                    )
                )
                eventCountDownTimer?.start()
            } else {
                tvEventStartTime.text = context.getString(R.string.label_finished)
                tvEventStartTime.setTextColor(ContextCompat.getColor(root.context, R.color.gray))
            }
        }

        private fun ItemEventBinding.setupFavoriteButton(event: Event, action: (Event) -> Unit) {
            ivFavorite.setOnClickListener {
                event.isFavorite = !event.isFavorite
                action(event)
                notifyItemChanged(adapterPosition)
            }
        }
    }
}





