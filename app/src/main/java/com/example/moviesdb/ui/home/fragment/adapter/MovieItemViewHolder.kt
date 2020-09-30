package com.example.moviesdb.ui.home.fragment.adapter

import android.view.View
import coil.api.load
import coil.transform.RoundedCornersTransformation
import com.example.moviesdb.R
import com.example.moviesdb.data.home.remote.model.Results
import com.example.moviesdb.ui.base.BaseViewHolder
import com.example.moviesdb.util.Constants.Companion.POSTER_BASE_URL
import com.example.moviesdb.util.OnItemClickListener
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieItemViewHolder(
    itemView: View?,
    val itemClickListener: OnItemClickListener
): BaseViewHolder<Results>(itemView) {



    override fun onBind(item: Results) {
        try {
            itemView.cv_iv_movie_poster.load(POSTER_BASE_URL.plus(item.poster_path)) {
                crossfade(true)
                    .error(R.drawable.ic_movie)
                    .placeholder(R.drawable.ic_movie)
                transformations(RoundedCornersTransformation(10F))
            }
            itemView.cv_movie_release_date.text = item.release_date
            itemView.cv_movie_title.text = item.title
            itemView.setOnClickListener { itemClickListener.onClick(item.id) }
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
    }

}