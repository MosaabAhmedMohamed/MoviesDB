package com.example.moviesdb.ui.home.fragment

import android.Manifest
import android.app.DownloadManager
import android.content.Context
import android.content.pm.PackageManager
import android.database.Cursor
import android.net.Uri
import android.os.Environment
import androidx.core.app.ActivityCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import coil.api.load
import coil.size.Scale
import com.example.moviesdb.R
import com.example.moviesdb.data.home.remote.model.MovieDetailModel
import com.example.moviesdb.presentation.BaseViewState
import com.example.moviesdb.presentation.home.viewmodel.MovieViewModel
import com.example.moviesdb.ui.base.BaseFragment
import com.example.moviesdb.util.Constants.Companion.ID_KEY
import com.example.moviesdb.util.Constants.Companion.POSTER_BASE_URL
import com.example.moviesdb.util.DownloadManagerUtil.getDirectory
import com.example.moviesdb.util.DownloadManagerUtil.getRequest
import com.example.moviesdb.util.DownloadManagerUtil.startDownload
import com.example.moviesdb.util.ReadWritePermission.REQUEST_WRITE_EXTERNAL_STORAGE
import com.example.moviesdb.util.ReadWritePermission.askForPermission
import com.example.moviesdb.util.ReadWritePermission.isPermissionGranted
import com.example.moviesdb.util.extintion.displayToast
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import okhttp3.ResponseBody

class MovieDetailFragment : BaseFragment(R.layout.fragment_movie_detail) {

    private val viewModel: MovieViewModel by viewModels {
        viewModelFactory
    }


    override fun init() {
        initMovieObserver()
        initFileObserver()
        checkArgs()
    }

    override fun onViewClicked() {
        ic_download.setOnClickListener {
            activity?.let {
                if (isPermissionGranted(it)) {
                    viewModel.imgUrl?.let { it1 -> downloadImage(it1) }
                } else {
                   askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                }
            }
        }
    }

    private fun checkArgs() {
        if (arguments != null && requireArguments().containsKey(ID_KEY)) {
            getMovie(requireArguments().getInt(ID_KEY))
        }
    }

    private fun getMovie(movieId: Int) {
        viewModel.movie(movieId)
    }

    private fun initFileObserver() {
        viewModel.fileVS.observe(
            viewLifecycleOwner,
            Observer<BaseViewState<ResponseBody>> { renderViewState(it) })
    }

    private fun initMovieObserver() {
        viewModel.movieVS.observe(
            viewLifecycleOwner,
            Observer<BaseViewState<MovieDetailModel>> { renderViewState(it) })
    }

    override fun <T> renderData(model: T) {
        super.renderData(model)
        when (model) {
            is MovieDetailModel -> setData(model)
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
            viewModel.imgUrl = POSTER_BASE_URL.plus(model.poster_path)
            iv_movie_poster.load(POSTER_BASE_URL.plus(model.poster_path)) {
                crossfade(true)
                    .error(R.drawable.ic_movie)
                    .placeholder(R.drawable.ic_movie)
                    .scale(Scale.FILL)
                //transformations(RoundedCornersTransformation())
            }
        } catch (e: NullPointerException) {
            e.printStackTrace()

        }
    }

    private fun downloadImage(url: String) {
        val downloadManager =
            activity?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        startDownload(downloadManager, getRequest(url, getDirectory().toString())) { loadStatus ->
            displayToast(loadStatus.toString())
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_WRITE_EXTERNAL_STORAGE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    viewModel.imgUrl?.let { downloadImage(it) }
                }
                return
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.movieVS.postValue(null)
    }
}