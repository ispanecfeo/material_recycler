package com.example.material_3.ui

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.coroutineScope
import coil.load
import com.example.material_3.R
import com.example.material_3.databinding.FragmentNasaPictureBinding
import com.example.material_3.domain.NasaRepositoryImpl

class NasaPictureFragment : Fragment(R.layout.fragment_nasa_picture) {

    companion object {
        private const val ARG_POSITION = "ARG_POSITION"
        fun newInstance(number: Int) =  NasaPictureFragment().apply {
            arguments = bundleOf(ARG_POSITION to number)
        }
    }

    private val viewModel : NasaViewModel by viewModels {
        NasaViewModelFactory(NasaRepositoryImpl())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val position = arguments?.getInt(ARG_POSITION) ?:1
        viewModel.requestPicture(position)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding = FragmentNasaPictureBinding.bind(view)

        viewLifecycleOwner.lifecycle.coroutineScope.launchWhenStarted {
            viewModel.image.collect() { url ->
                url?.let {
                    binding.img.load(it)
                }

            }
        }
    }

}