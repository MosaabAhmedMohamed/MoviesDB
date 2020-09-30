package com.example.moviesdb.ui.home.fragment

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.moviesdb.R
import com.example.moviesdb.presentation.home.viewmodel.MoviesViewModel
import com.example.moviesdb.ui.base.BaseFragment
import com.example.moviesdb.ui.base.RetryDialog
import com.example.moviesdb.ui.home.fragment.adapter.HeaderFooterAdapter
import com.example.moviesdb.ui.home.fragment.adapter.MoviesAdapter
import com.example.moviesdb.util.Constants.Companion.ID_KEY
import com.example.moviesdb.util.OnItemClickListener
import kotlinx.android.synthetic.main.fragment_movies.*


class MoviesFragment :BaseFragment(R.layout.fragment_movies),OnItemClickListener,RetryDialog.onRetryListener{

    private val viewModel: MoviesViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var adapter: MoviesAdapter

    override fun init() {
        if (!this::adapter.isInitialized) {
            adapter =
                MoviesAdapter(
                    this,
                    DIFF_CALLBACK = MoviesAdapter.DIFF_CALLBACK
                )
        }
        initMoviesRv()
        initMoviesListObserver()
    }

    private fun initMoviesRv() {
        rv.setHasFixedSize(true)
        rv.layoutManager = GridLayoutManager(context, 3)
        rv.adapter = adapter.withLoadStateHeaderAndFooter(
            header = HeaderFooterAdapter(
                adapter
            ),
            footer = HeaderFooterAdapter(
                adapter
            )
        )
        adapter.addLoadStateListener {
            showLoading(it.refresh is LoadState.Loading)
            showRetryDialog(it.refresh is LoadState.Error)
        }

        refresh_layout.setOnRefreshListener { adapter.refresh() }
    }


    private fun initMoviesListObserver() {
        if (adapter.itemCount == 0)
            viewModel.moviesList().observe(requireActivity(), Observer {
                refresh_layout?.stopRefresh()
                adapter.submitData(lifecycle, it)
            })
    }


    override fun onViewClicked() {
    }

    override fun onClick(id: Int) {
        val args = Bundle()
        args.putInt(ID_KEY, id)
        navigate(R.id.action_moviesFragment_to_movieDetailFragment, args)
    }

    override fun onRetry() {
        adapter.retry()
    }

}
