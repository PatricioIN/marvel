package com.marvel.talentomobile.app.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.ViewModelProvider
import com.marvel.talentomobile.app.data.model.MarvelCharacter
import com.marvel.talentomobile.app.databinding.DetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    //Binding
    private lateinit var binding: DetailFragmentBinding

    //Viewmodel
    private lateinit var detailViewModel: DetailViewModel

    //Bottom Sheets
    private var bottomSheetComics = BottomSheetComics()

    //Variables
    var marvelCharacter: MarvelCharacter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DetailFragmentBinding.inflate(inflater, container, false)
        setFragmentResultListener("homeBundleToDetail") { _, bundle ->
            marvelCharacter = bundle.getParcelable("marvelCharacter")
            marvelCharacter?.let { detailViewModel.getMarvelCharacter(it.id) }
        }
        setUpViewModels()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.showComicsButton.setOnClickListener {
            showBottomSheetComics()
        }
    }
    private fun setUpViewModels() {
        detailViewModel = ViewModelProvider(this)[DetailViewModel::class.java]
        binding.viewModel = detailViewModel
        binding.lifecycleOwner = this
    }

    private fun showBottomSheetComics(){
        if(bottomSheetComics.isAdded) return
        bottomSheetComics = BottomSheetComics()
        val bundle = Bundle()
        bundle.putParcelableArrayList("comics", marvelCharacter?.comics?.items)
        bottomSheetComics.arguments = bundle
        bottomSheetComics.show(parentFragmentManager, BottomSheetComics.TAG)
    }
}