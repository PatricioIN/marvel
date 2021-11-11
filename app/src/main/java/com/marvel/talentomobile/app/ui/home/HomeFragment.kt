package com.marvel.talentomobile.app.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.marvel.talentomobile.app.R
import com.marvel.talentomobile.app.databinding.HomeFragmentBinding
import com.marvel.talentomobile.app.ui.detail.DetailFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    //Binding
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    //Viewmodel
    private val homeViewModel: HomeViewModel by activityViewModels()

    //Adapters
    private lateinit var homeListAdapter: HomeListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUp()
    }

    private fun setUp() = with(binding){

        //Adapter
        homeListAdapter = HomeListAdapter { marvelCharacter ->
            setFragmentResult("homeBundleToDetail",
                bundleOf("marvelCharacter" to marvelCharacter)
            )
            findNavController().navigate(R.id.action_home_to_detail)
        }

        //Recycler
        marvelCharsRecycler.apply {
            adapter = homeListAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = null
            addItemDecoration(DividerItemDecoration(
                marvelCharsRecycler.context,
                (marvelCharsRecycler.layoutManager as LinearLayoutManager).orientation
            ))
        }

        //Get marvel characters
        lifecycleScope.launch {
            homeViewModel.getMarvelCharacters(0).collectLatest { pagingData ->
                homeListAdapter.submitData(pagingData)
            }
        }
    }
}