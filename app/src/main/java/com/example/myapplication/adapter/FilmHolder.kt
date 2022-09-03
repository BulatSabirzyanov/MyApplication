package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.myapplication.Film
import com.example.myapplication.databinding.ItemFilmBinding

class FilmHolder(
    private val binding: ItemFilmBinding,
    private val navigate: (Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private var film: Film? = null

    private val options = RequestOptions()
        .priority(Priority.HIGH)
        .diskCacheStrategy(DiskCacheStrategy.ALL)

    fun bind(item: Film) {
        this.film = item
        with(binding) {
            tvName.text = item.name
            itemView.setOnClickListener {
                navigate(item.id)
            }
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            navigate: (Int) -> Unit
        ) = FilmHolder(
            ItemFilmBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), navigate
        )
    }
}