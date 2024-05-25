package com.japago.vinilosmusic202412.adapters

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.japago.vinilosmusic202412.DetalleBanda
import com.japago.vinilosmusic202412.R
import com.japago.vinilosmusic202412.data.model.Band
import com.japago.vinilosmusic202412.databinding.BandItemBinding

class BandsAdapter: RecyclerView.Adapter<BandsAdapter.BandsViewHolder>() {

    var bands :List<Band> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BandsViewHolder {
        val withDataBinding: BandItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            BandsViewHolder.LAYOUT,
            parent,
            false)
        return BandsViewHolder(withDataBinding)
    }

    override fun onBindViewHolder(holder: BandsViewHolder, position: Int) {
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

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetalleBanda::class.java)
                intent.putExtra("id_band", viewDataBinding.txtBandId.text)
                intent.putExtra("name_band", viewDataBinding.txtName.text)
                intent.putExtra("image_band", viewDataBinding.txtBandImage.text)
                intent.putExtra("date_band", viewDataBinding.txtBandDate.text)
                intent.putExtra("description_band", viewDataBinding.txtBandDescription.text)
                itemView.context.startActivity(intent)
            }
        }
    }
}