package com.godbeom.baseapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.godbeom.baseapp.R
import com.godbeom.baseapp.databinding.ItemMatchBinding
import com.godbeom.baseapp.persistence.entity.Matchs

class MatchGridViewHolder(private val binding:ItemMatchBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(match: Matchs.Match, position:Int){
        with(match){
            binding.poster.load(hospImage?.original){
                crossfade(true)
            }
            binding.label.text = "$hosp_nm + $position + $hosp_id"
        }
    }

    fun onImgClick(){

    }


    companion object{
        fun create(parent: ViewGroup): MatchGridViewHolder{
            val itemMatchView = LayoutInflater.from(parent.context).inflate(R.layout.item_match, parent, false)
            val binding = ItemMatchBinding.bind(itemMatchView)
            binding.poster.scaleType = ImageView.ScaleType.FIT_CENTER
            return MatchGridViewHolder(binding)
        }
    }
}