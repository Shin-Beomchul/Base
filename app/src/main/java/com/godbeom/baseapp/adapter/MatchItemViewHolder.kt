package com.godbeom.baseapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.godbeom.baseapp.R
import com.godbeom.baseapp.databinding.ItemMatchBinding
import com.godbeom.baseapp.persistence.entity.Matchs

class MatchItemViewHolder(parent:ViewGroup, private val itemCallback: (Matchs.Match) -> Unit)
    : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
){
    private val binding = ItemMatchBinding.bind(itemView)

    fun bind(match: Matchs.Match, position:Int){
        with(match){
            binding.poster.load(hospImage?.original){
                crossfade(true)
            }
            binding.label.text = "$hosp_nm + $position + $hosp_id"
            binding.root.setOnClickListener {
                itemCallback.invoke(match)
            }
        }
    }


}