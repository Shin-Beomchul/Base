package com.godbeom.baseapp.adapter

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
class MatchFooterLoadStateAdapter(
        private val adapter: MatchRxPagingAdapter
) : LoadStateAdapter<MatchFooterViewHolder>() {
    override fun onBindViewHolder(holder: MatchFooterViewHolder, loadState: LoadState) {
        holder.bindTo(loadState)
    }

    override fun onCreateViewHolder(
            parent: ViewGroup,
            loadState: LoadState
    ): MatchFooterViewHolder {
        return MatchFooterViewHolder(parent) { adapter.retry() }
    }
}