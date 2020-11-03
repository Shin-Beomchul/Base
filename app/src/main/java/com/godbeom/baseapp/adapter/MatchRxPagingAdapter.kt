package com.godbeom.baseapp.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.godbeom.baseapp.persistence.entity.Matchs

//DiffUtil 구필수.

class MatchRxPagingAdapter: PagingDataAdapter<Matchs.Match, MatchGridViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: MatchGridViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchGridViewHolder {
      return MatchGridViewHolder.create(parent)
    }


    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Matchs.Match>() {
            override fun areItemsTheSame(oldItem: Matchs.Match, newItem: Matchs.Match): Boolean {
                return oldItem.hosp_id == newItem.hosp_id
            }

            override fun areContentsTheSame(oldItem: Matchs.Match, newItem: Matchs.Match): Boolean {
                return oldItem == newItem
            }
        }
    }


}

