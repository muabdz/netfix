package com.muabdz.home.presentation.ui.homefeeds

import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.muabdz.core.base.BaseFragment
import com.muabdz.home.databinding.FragmentHomeFeedsBinding
import com.muabdz.home.presentation.adapter.viewholder.HomeAdapter
import com.muabdz.home.presentation.adapter.viewholder.HomeAdapterClickListener
import com.muabdz.home.presentation.ui.home.HomeViewModel
import com.muabdz.shared.data.model.viewparam.MovieViewParam
import com.muabdz.shared.utils.ext.subscribe
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class HomeFeedsFragment : BaseFragment<FragmentHomeFeedsBinding, HomeViewModel>(
    FragmentHomeFeedsBinding::inflate
) {

    override val viewModel: HomeViewModel by sharedViewModel()

    private val recyclerViewPool: RecyclerView.RecycledViewPool by lazy {
        RecyclerView.RecycledViewPool()
    }

    private val homeAdapter: HomeAdapter by lazy {
        HomeAdapter(object : HomeAdapterClickListener {
            override fun onMyListClicked(movieViewParam: MovieViewParam) {
                // TODO: add to watchlist
            }

            override fun onPlayMovieClicked(movieViewParam: MovieViewParam) {
                // TODO: go to player
            }

            override fun onMovieClicked(movieViewParam: MovieViewParam) {
                // TODO: show movie info
            }
        }, recyclerViewPool)
    }

    private fun setupRecyclerView() {
        binding.rvHome.apply {
            adapter = homeAdapter
            layoutManager = LinearLayoutManager(requireContext())
            setRecycledViewPool(recycledViewPool)
            // TODO: add scroll listener
        }
    }


    override fun initView() {
        setupRecyclerView()
        initData()
    }

    override fun observeData() {
        super.observeData()
        viewModel.homeFeedsResult.observe(this) {
            it.subscribe(
                doOnSuccess = { result ->
                    showLoading(false)
                    result.payload?.let { data ->
                        homeAdapter.setItems(data)
                    }
                },
                doOnLoading = {
                    showLoading(true)
                },
                doOnError = { error ->
                    showLoading(false)
                    error.exception?.let { e -> showError(true, e) }

                })
        }
        // TODO: add observe data
    }

    private fun initData() {
        viewModel.getCurrentUser()
        viewModel.fetchHome()
    }

    private fun showLoading(isShowLoading: Boolean) {
        binding.pbHome.isVisible = isShowLoading
    }

}