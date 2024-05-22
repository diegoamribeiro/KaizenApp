package com.dmribeiro87.kaizenapp.gamesFeature.presentation.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dmribeiro87.kaizenapp.R
import com.dmribeiro87.kaizenapp.databinding.ItemHeaderBinding
import com.dmribeiro87.kaizenapp.gamesFeature.domain.model.Sports

class SportsAdapter(private val sports: List<Sports>) :
    RecyclerView.Adapter<SportsAdapter.SportsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SportsViewHolder {
        val binding = ItemHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SportsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SportsViewHolder, position: Int) {
        val sport = sports[position]
        holder.bind(sport)
    }

    override fun getItemCount(): Int = sports.size

    inner class SportsViewHolder(private val binding: ItemHeaderBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(sport: Sports) {
            binding.tvSportName.text = sport.name
            binding.rvEvents.layoutManager = GridLayoutManager(itemView.context, 3)
            val eventsAdapter = EventsAdapter(sport.events)
            binding.rvEvents.adapter = eventsAdapter

            binding.ivExpandCollapse.setOnClickListener {
                if (binding.rvEvents.visibility == View.GONE) {
                    binding.rvEvents.visibility = View.VISIBLE
                    binding.ivExpandCollapse.setImageResource(R.drawable.ic_expand_less)
                } else {
                    binding.rvEvents.visibility = View.GONE
                    binding.ivExpandCollapse.setImageResource(R.drawable.ic_expand_more)
                }
            }

            binding.switchFavorites.setOnCheckedChangeListener { _, isChecked ->
                eventsAdapter.filterFavorites(isChecked)
            }
        }
    }
}


