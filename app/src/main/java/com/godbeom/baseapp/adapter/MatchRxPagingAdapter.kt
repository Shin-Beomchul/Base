package com.godbeom.baseapp.adapter

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.godbeom.baseapp.persistence.entity.Matchs

//DiffUtil 구필수.

class MatchRxPagingAdapter: PagingDataAdapter<Matchs.Match, MatchItemViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: MatchItemViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchItemViewHolder {
      return MatchItemViewHolder.create(parent)
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> {
                HEADER
            }
            itemCount -> {
                FOOTER_NETWOK_LOADING
            }
            else -> {
                MATCH_ITEM
            }
        }
    }

    companion object {
        const val HEADER = 1
        const val MATCH_ITEM = 2
        const val FOOTER_NETWOK_LOADING = 3
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

