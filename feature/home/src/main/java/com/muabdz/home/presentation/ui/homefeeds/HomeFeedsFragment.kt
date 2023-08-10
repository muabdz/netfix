package com.muabdz.home.presentation.ui.homefeeds

import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.muabdz.core.base.BaseFragment
import com.muabdz.home.R
import com.muabdz.home.databinding.FragmentHomeFeedsBinding
import com.muabdz.home.presentation.adapter.viewholder.HomeAdapter
import com.muabdz.home.presentation.adapter.viewholder.HomeAdapterClickListener
import com.muabdz.home.presentation.ui.home.HomeViewModel
import com.muabdz.shared.data.model.viewparam.MovieViewParam
import com.muabdz.shared.utils.ColorUtils
import com.muabdz.shared.utils.ext.subscribe
import com.muabdz.shared.utils.textdrawable.ColorGenerator
import com.muabdz.shared.utils.textdrawable.TextDrawable
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import kotlin.math.min


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
                viewModel.addOrRemoveWatchlist(movieViewParam)
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
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    val scrollY: Int = binding.rvHome.computeVerticalScrollOffset()
                    val color = ColorUtils.changeAlpha(
                        ContextCompat.getColor(requireActivity(), R.color.black_transparent),
                        (min(255, scrollY).toFloat() / 255.0f).toDouble()
                    )
                    binding.clToolbarHomeFeed.setBackgroundColor(color)
                }
            })
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
        viewModel.currentUserResult.observe(this) {
            it.subscribe(doOnSuccess = { result ->
                binding.ivAvatarUser.setImageDrawable(
                    TextDrawable.builder()
                        .beginConfig()
                        .bold()
                        .toUpperCase()
                        .endConfig()
                        .buildRect(
                            result.payload?.username?.get(0).toString(),
                            ColorGenerator.MATERIAL.randomColor
                        )
                )
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