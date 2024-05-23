package com.dmribeiro87.kaizenapp.gamesFeature.presentation.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dmribeiro87.kaizenapp.R
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
                tvSportName.text = sport.name
                setupRecyclerView(sport)
                setupExpandCollapseButton()
                setupFavoritesSwitch(sport, context)
            }
        }

        private fun ItemHeaderBinding.setupRecyclerView(sport: Sports) {
            rvEvents.layoutManager = GridLayoutManager(itemView.context, 3)
            val eventsAdapter = EventsAdapter(sport.events.sortedByDescending { it.startTime })
            eventsAdapter.setAction { event ->
                action?.invoke(event)
            }
            rvEvents.adapter = eventsAdapter
        }

        private fun ItemHeaderBinding.setupExpandCollapseButton() {
            ivExpandCollapse.setOnClickListener {
                if (rvEvents.visibility == View.GONE) {
                    rvEvents.visibility = View.VISIBLE
                    ivExpandCollapse.setImageResource(R.drawable.ic_expand_less)
                } else {
                    rvEvents.visibility = View.GONE
                    ivExpandCollapse.setImageResource(R.drawable.ic_expand_more)
                }
            }
        }

        private fun ItemHeaderBinding.setupFavoritesSwitch(sport: Sports, context: Context) {
            switchFavorites.setOnCheckedChangeListener { _, isChecked ->
                (rvEvents.adapter as? EventsAdapter)?.filterFavorites(isChecked)
                if (rvEvents.adapter?.itemCount == 0) {
                    rvEvents.visibility = View.GONE
                    tvEmptyState.visibility = View.VISIBLE
                    tvEmptyState.text = if (isChecked) {
                        context.getString(R.string.text_no_favorites_yet)
                    } else {
                        context.getString(R.string.text_no_matches)
                    }
                } else {
                    rvEvents.visibility = View.VISIBLE
                    tvEmptyState.visibility = View.GONE
                }
            }
        }
    }
}



