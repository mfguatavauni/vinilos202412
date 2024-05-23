package com.japago.vinilosmusic202412.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.japago.vinilosmusic202412.R
import com.japago.vinilosmusic202412.data.model.Band
import com.japago.vinilosmusic202412.databinding.BandDetailItemBinding

class BandsDetailAdapter: RecyclerView.Adapter<BandsDetailAdapter.BandsDetailViewHolder>() {


    var band : Band? = null
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BandsDetailViewHolder {
        val withDataBinding: BandDetailItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            BandsDetailViewHolder.LAYOUT,
            parent,
            false)
        return BandsDetailViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: BandsDetailViewHolder, position: Int) {
        holder.viewDataBinding.also {
            it.bandDetail = band
        }
    }

    override fun getItemCount(): Int {
        return 1
    }

    class BandsDetailViewHolder(val viewDataBinding: BandDetailItemBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        companion object {
            @LayoutRes
            val LAYOUT = R.layout.band_detail_item
        }
    }
}