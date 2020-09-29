package com.example.moviesdb.ui.home.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.example.moviesdb.R
import com.example.moviesdb.data.home.remote.model.Results
import com.example.moviesdb.util.OnItemClickListener

class MoviesAdapter(
    private val onItemClickListener: OnItemClickListener,
    DIFF_CALLBACK: DiffUtil.ItemCallback<Results>
) : PagingDataAdapter<Results, MovieItemViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieItemViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MovieItemViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: MovieItemViewHolder, position: Int) {
        val item: Results? = getItem(position)
        // Note that item may be null. ViewHolder must support binding a
        // null item as a placeholder.
        item?.let { holder.onBind(it) }
    }


    object DIFF_CALLBACK : DiffUtil.ItemCallback<Results>() {
        override fun areItemsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Results, newItem: Results): Boolean {
            return oldItem.equals(newItem)
        }
    }

}