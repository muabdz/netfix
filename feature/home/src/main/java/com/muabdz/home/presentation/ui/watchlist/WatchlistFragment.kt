package com.muabdz.home.presentation.ui.watchlist

import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.muabdz.core.base.BaseFragment
import com.muabdz.home.R
import com.muabdz.home.databinding.FragmentWatchlistBinding
import com.muabdz.home.presentation.adapter.MovieAdapter
import com.muabdz.home.presentation.ui.home.HomeViewModel
import com.muabdz.shared.utils.ext.subscribe
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class WatchlistFragment : BaseFragment<FragmentWatchlistBinding, HomeViewModel>(
    FragmentWatchlistBinding::inflate
) {
    override val viewModel: HomeViewModel by sharedViewModel()

    private val movieAdapter: MovieAdapter by lazy {
        MovieAdapter(
            true
        ) {
            // TODO: on movie clicked
        }
    }

    override fun initView() {
        with(binding.rvWatchlist) {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(requireContext(), 3)
        }
        binding.srlWatchlist.setOnRefreshListener {
            viewModel.fetchWatchlist()
            binding.srlWatchlist.isRefreshing = false
        }
        viewModel.fetchWatchlist()
    }

    override fun observeData() {
        super.observeData()
        viewModel.watchlistResult.observe(this) {
            it.subscribe(
                doOnSuccess = { result ->
                    showLoading(false)
                    result.payload?.let { data ->
                        movieAdapter.setItems(data)
                    }
                },
                doOnLoading = {
                    binding.tvErrorWatchlist.isVisible = false
                    showLoading(true)
                },
                doOnError = { error ->
                    showLoading(false)
                    binding.tvErrorWatchlist.isVisible = true
                    binding.tvErrorWatchlist.text = error.exception?.message
                },
                doOnEmpty = {
                    showLoading(false)
                    binding.tvErrorWatchlist.isVisible = true
                    binding.tvErrorWatchlist.text = getString(R.string.text_empty_watchlist)
                }
            )
        }
    }

    private fun showLoading(isShowLoading: Boolean) {
        binding.pbWatchlist.isVisible = isShowLoading
        binding.rvWatchlist.isVisible = !isShowLoading
    }
}