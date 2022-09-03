package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.FragmentFilmBinding

class FilmFragment : Fragment() {
    private lateinit var binding: FragmentFilmBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val id = arguments?.getInt("Id")
        if (id != null) {
            val film = FilmRepository.films[id]
            with(binding) {
                Glide.with(this.root).load(film.url).into(ivPoster)
                name.text = film.name
            }
        }
    }
}