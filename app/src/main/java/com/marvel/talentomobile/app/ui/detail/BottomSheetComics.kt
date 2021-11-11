package com.marvel.talentomobile.app.ui.detail

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.fragment.app.DialogFragment
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.marvel.talentomobile.app.R
import com.marvel.talentomobile.app.data.model.Item
import com.marvel.talentomobile.app.databinding.BottomSheetComicsBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class BottomSheetComics : BottomSheetDialogFragment() {

    private var _binding: BottomSheetComicsBinding? = null
    private val binding get() = _binding!!

    private lateinit var comicsListAdapter: ComicsListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = BottomSheetComicsBinding.inflate(inflater, container, false)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogStyle)
        val comics =  arguments?.getParcelableArrayList<Item>("comics")
        setUpRecycler(comics)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            val parentLayout = bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { view ->
                val behaviour = BottomSheetBehavior.from(view)
                setupFullHeight(view)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    private fun setUpRecycler(comics: ArrayList<Item>?) = with(binding){
        //Adapters
        setUpAdapters(comics)

        //Recycler
        comicsRecyclerView.apply {
            adapter = comicsListAdapter
            layoutManager = LinearLayoutManager(context)
            itemAnimator = null
            comicsRecyclerView.addItemDecoration(DividerItemDecoration(
                comicsRecyclerView.context,
                (comicsRecyclerView.layoutManager as LinearLayoutManager).orientation
            ))
        }
    }

    private fun setUpAdapters(comics: ArrayList<Item>?) {
        //Recycler
        comicsListAdapter = ComicsListAdapter { comic ->
           println(comic)
        }
        comics?.let { comicsListAdapter.setData(it) }
    }

    companion object {
        const val TAG = "BottomSheetComic"
    }
}