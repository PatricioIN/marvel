package com.marvel.talentomobile.app.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.marvel.talentomobile.app.data.model.MarvelCharacter
import com.marvel.talentomobile.app.databinding.ItemHomeRecyclerBinding

class HomeListAdapter(
    private val listener: (MarvelCharacter) -> Unit) :
    PagingDataAdapter <MarvelCharacter, HomeListAdapter.ViewHolder>(ITEMS_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemHomeRecyclerBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener { getItem(position)?.let { selectedMarvelCharacter -> listener.invoke(selectedMarvelCharacter) } }
        getItem(position)?.let { selectedMarvelCharacter -> holder.bind(selectedMarvelCharacter) }
    }

    class ViewHolder(private val itemBinding: ItemHomeRecyclerBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(marvelCharacter: MarvelCharacter) {
            with(itemBinding){
                name.text = marvelCharacter.name
                Glide.with(itemView.context)
                    .load(marvelCharacter.thumbnail.path + "." +  marvelCharacter.thumbnail.extension)
                    .into(image)
            }
        }
    }

    companion object {
        private val ITEMS_COMPARATOR = object : DiffUtil.ItemCallback<MarvelCharacter>() {
            override fun areItemsTheSame(oldItem: MarvelCharacter, newItem: MarvelCharacter): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: MarvelCharacter, newItem: MarvelCharacter): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}