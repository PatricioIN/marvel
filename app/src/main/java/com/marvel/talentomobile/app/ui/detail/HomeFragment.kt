package com.marvel.talentomobile.app.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.marvel.talentomobile.app.R
import com.marvel.talentomobile.app.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    //Binding
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = HomeFragment()
    }

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
            //setFragmentResult("homeBundleToSearch", bundleOf("bubbleFilter" to bubbleFilter))
            //findNavController().navigate(R.id.action_home_to_search_results)
        }

        //Recycler
        marvelCharsRecycler.apply {
            adapter = homeListAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = null
        }

        //Get marvel characters
        lifecycleScope.launch {
            homeViewModel.getMarvelCharacters(0).collectLatest { pagingData ->
                homeListAdapter.submitData(pagingData)
            }
        }
    }
}