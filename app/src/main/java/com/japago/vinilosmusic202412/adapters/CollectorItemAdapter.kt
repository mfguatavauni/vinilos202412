package com.japago.vinilosmusic202412.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.japago.vinilosmusic202412.R
import com.japago.vinilosmusic202412.data.model.Album
import com.japago.vinilosmusic202412.databinding.AlbumesCollectorBinding

class CollectorItemAdapter : RecyclerView.Adapter<CollectorItemAdapter.CollectorViewHolder>(){

    var albumes :List<Album> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectorViewHolder {
        val withDataBinding: AlbumesCollectorBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            CollectorViewHolder.LAYOUT,
            parent,
            false)
        return CollectorViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: CollectorViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.album = albumes[position]
        }
    }

    override fun getItemCount(): Int {
        return albumes.size
    }

    class CollectorViewHolder(val viewDataBinding: AlbumesCollectorBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.albumes_collector
        }
    }
}