package com.dmribeiro87.kaizenapp.gamesFeature.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dmribeiro87.kaizenapp.R
import com.dmribeiro87.kaizenapp.core.util.capitalizeFirstLetter
import com.dmribeiro87.kaizenapp.core.util.hide
import com.dmribeiro87.kaizenapp.core.util.show
import com.dmribeiro87.kaizenapp.databinding.ItemHeaderBinding
import com.dmribeiro87.kaizenapp.gamesFeature.domain.model.Event
import com.dmribeiro87.kaizenapp.gamesFeature.domain.model.Sports

class SportsAdapter(private val sports: List<Sports>) :
    RecyclerView.Adapter<SportsAdapter.SportsViewHolder>() {

    private var action: ((Event) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportsViewHolder {
        val binding = ItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SportsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SportsViewHolder, position: Int) {
        val sport = sports[position]
        holder.bind(sport, holder.itemView.context)
    }

    override fun getItemCount(): Int = sports.size

    fun setAction(action: (Event) -> Unit) {
        this.action = action
    }

    inner class SportsViewHolder(private val binding: ItemHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(sport: Sports, context: Context) {
            binding.apply {
                tvSportName.text = sport.name.capitalizeFirstLetter()
                setupRecyclerView(sport, context)
                setupExpandCollapseButton()
                setupFavoritesSwitch(sport, context)
            }
        }

        private fun ItemHeaderBinding.setupRecyclerView(sport: Sports, context: Context) {
            val currentTime = System.currentTimeMillis() / 1000
            val sortedEvents = sport.events.sortedWith(
                compareBy<Event> { it.startTime > currentTime }.thenBy { it.startTime }
            ).sortedWith(compareBy { it.startTime <= currentTime })
            rvEvents.layoutManager = GridLayoutManager(context, 3)
            val eventsAdapter = EventsAdapter(sortedEvents)
            eventsAdapter.setAction { event ->
                action?.invoke(event)
            }
            rvEvents.adapter = eventsAdapter
        }

        private fun ItemHeaderBinding.setupExpandCollapseButton() {
            ivExpandCollapse.setOnClickListener {
                if (rvEvents.visibility == View.GONE) {
                    rvEvents.show()
                    ivExpandCollapse.setImageResource(R.drawable.ic_expand_less)
                } else {
                    rvEvents.hide()
                    ivExpandCollapse.setImageResource(R.drawable.ic_expand_more)
                }
            }
        }

        private fun ItemHeaderBinding.setupFavoritesSwitch(sport: Sports, context: Context) {
            switchFavorites.setOnCheckedChangeListener { _, isChecked ->
                (rvEvents.adapter as? EventsAdapter)?.filterFavorites(isChecked)
                if (rvEvents.adapter?.itemCount == 0) {
                    rvEvents.hide()
                    tvEmptyState.show()
                    tvEmptyState.text = if (isChecked) {
                        context.getString(R.string.text_no_favorites_yet)
                    } else {
                        context.getString(R.string.text_no_matches)
                    }
                } else {
                    rvEvents.show()
                    tvEmptyState.hide()
                }
            }
        }
    }
}
