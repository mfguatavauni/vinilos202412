package com.japago.vinilosmusic202412.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.japago.vinilosmusic202412.DetalleAlbumActivity
import com.japago.vinilosmusic202412.R
import com.japago.vinilosmusic202412.data.model.AlbumesItem

class AlbumAdapter(private val albumList: List<AlbumesItem>) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    inner class AlbumViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val albumCover: ImageView = view.findViewById(R.id.albumCover)
        val albumName: TextView = view.findViewById(R.id.albumName)
        val albumGenre: TextView = view.findViewById(R.id.albumGenre)

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetalleAlbumActivity::class.java)
                intent.putExtra("id_album", albumList[bindingAdapterPosition].id.toInt())
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val album = albumList[position]
        holder.albumName.text = album.name
        holder.albumGenre.text = album.genre
        Glide.with(holder.albumCover.context).load(album.cover).into(holder.albumCover)
    }

    override fun getItemCount() = albumList.size
}