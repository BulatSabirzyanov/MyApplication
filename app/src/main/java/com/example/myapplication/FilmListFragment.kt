package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.myapplication.adapter.FilmAdapter
import com.example.myapplication.databinding.FragmentFilmsBinding

class FilmListFragment : Fragment() {
    private var binding: FragmentFilmsBinding? = null
    private var filmAdapter: FilmAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFilmsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = Bundle()
        filmAdapter = FilmAdapter(FilmRepository.films) {
            bundle.putInt("Id", it)
            findNavController().navigate(R.id.action_filmListFragment_to_filmFragment, bundle)
        }
        binding?.listView?.adapter = filmAdapter
    }
}
