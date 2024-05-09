package com.japago.vinilosmusic202412.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.japago.vinilosmusic202412.R
import com.japago.vinilosmusic202412.data.model.Band
import com.japago.vinilosmusic202412.databinding.BandItemBinding

class BandsAdapter: RecyclerView.Adapter<BandsAdapter.BandsViewHolder>() {

    var bands :List<Band> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BandsAdapter.BandsViewHolder {
        val withDataBinding: BandItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            BandsAdapter.BandsViewHolder.LAYOUT,
            parent,
            false)
        return BandsAdapter.BandsViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: BandsAdapter.BandsViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.band = bands[position]
        }
    }

    override fun getItemCount(): Int {
        return bands.size
    }

    class BandsViewHolder(val viewDataBinding: BandItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.band_item
        }
    }
}