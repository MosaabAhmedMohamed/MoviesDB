package com.example.moviesdb.ui.home.fragment

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import coil.api.load
import coil.size.Scale
import coil.transform.RoundedCornersTransformation
import com.example.moviesdb.R
import com.example.moviesdb.data.home.remote.model.MovieDetailModel
import com.example.moviesdb.presentation.BaseViewState
import com.example.moviesdb.presentation.home.viewmodel.MovieViewModel
import com.example.moviesdb.ui.base.BaseFragment
import com.example.moviesdb.util.Constants.Companion.ID_KEY
import com.example.moviesdb.util.Constants.Companion.POSTER_BASE_URL
import kotlinx.android.synthetic.main.fragment_movie_detail.*

class MovieDetailFragment : BaseFragment(R.layout.fragment_movie_detail) {

    private val viewModel: MovieViewModel by viewModels {
        viewModelFactory
    }


    override fun init() {
        initMovieObserver()
        checkArgs()
    }

    override fun onViewClicked() {
        TODO("Not yet implemented")
    }

    private fun checkArgs() {
        if (arguments != null && requireArguments().containsKey(ID_KEY)) {
            getMovie(requireArguments().getInt(ID_KEY))
        }
    }

    private fun getMovie(movieId: Int) {
        viewModel.movie(movieId)
    }

    private fun initMovieObserver() {
        viewModel.movieVS.observe(
            viewLifecycleOwner,
            Observer<BaseViewState<MovieDetailModel>> { renderViewState(it) })
    }

    override fun <T> renderData(model: T) {
        super.renderData(model)
        if (model is MovieDetailModel) {
            setData(model)
        }
    }

    private fun setData(model: MovieDetailModel) {
        try {
            movie_title.text = model.title
            movie_tagline.text = model.tagline
            movie_release_date.text = model.releaseDate
            movie_rating.text = model.voteAverage.toString()
            movie_runtime.text = model.runtime.toString().plus(" minutes")
            movie_overview.text = model.overview
            movie_release_date.text = model.releaseDate
            movie_budget.text = model.budget.toString()
            movie_revenue.text = model.revenue.toString()
            iv_movie_poster.load(POSTER_BASE_URL.plus(model.poster_path)) {
                crossfade(true)
                    .error(R.drawable.ic_movie)
                    .placeholder(R.drawable.ic_movie)
                    .scale(Scale.FILL)
                transformations(RoundedCornersTransformation())
            }

        } catch (e: NullPointerException) {
            e.printStackTrace()

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.movieVS.postValue(null)
    }
}