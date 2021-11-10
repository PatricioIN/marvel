package com.marvel.talentomobile.app.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import com.marvel.talentomobile.app.data.model.MarvelCharacter
import com.marvel.talentomobile.app.databinding.DetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    //Binding
    private lateinit var binding: DetailFragmentBinding

    //Viewmodel
    private val detailViewModel: DetailViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        setFragmentResultListener("homeBundleToDetail") { _, bundle ->
            val marvelCharacter: MarvelCharacter? = bundle.getParcelable("marvelCharacter")
            marvelCharacter?.let { detailViewModel.getMarvelCharacter(it.id) }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        detailViewModel.marvelCharacterDetail.observe(viewLifecycleOwner, { marvelCharacterDetail ->
            updateUI(marvelCharacterDetail[0])
        })
    }

    private fun updateUI(marvelCharacter: MarvelCharacter) = with(binding) {
        marvelCharName.text = marvelCharacter.name
    }
}