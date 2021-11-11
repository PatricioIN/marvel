package com.marvel.talentomobile.app.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.marvel.talentomobile.app.data.model.Item
import com.marvel.talentomobile.app.databinding.ItemHomeRecyclerBinding

class ComicsListAdapter(private val listener: (Item) -> Unit) : ListAdapter<Item, ComicsListAdapter.ViewHolder>(
    ITEM_COMPARATOR) {
    private var comicsList = mutableListOf<Item>()

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
        val comic = getItem(position)
        holder.itemView.setOnClickListener { listener.invoke(comic) }
        holder.bind(comic)
    }

    class ViewHolder(private val itemBinding: ItemHomeRecyclerBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(comic: Item) {
            itemBinding.name.text = comic.name
        }
    }

    fun setData(list: MutableList<Item>){
        this.comicsList = list
        submitList(list)
    }

    companion object {
        private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
                return oldItem.name == newItem.name
            }
        }
    }
}