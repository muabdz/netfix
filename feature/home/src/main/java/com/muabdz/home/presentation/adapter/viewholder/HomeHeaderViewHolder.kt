package com.muabdz.home.presentation.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.muabdz.home.databinding.ItemHeaderHomeBinding
import com.muabdz.home.presentation.viewparam.homeitem.HomeUiItem
import com.muabdz.shared.utils.CommonUtils

class HomeHeaderViewHolder(
    private val binding: ItemHeaderHomeBinding,
    private val listener: HomeAdapterClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bindView(item: HomeUiItem.HeaderSectionItem) {
        with(item.movieViewParam) {
            binding.tvAddToWatchlistHeader.setCompoundDrawablesWithIntrinsicBounds(
                0,
                CommonUtils.getWatchlistIcon(isUserWatchlist), 0, 0
            )
            binding.ivHeaderMovie.load(this.posterUrl)
            binding.tvTitleMovie.text = this.title
            binding.tvInfoHeader.setOnClickListener {
                listener.onMovieClicked(this)
            }
            binding.tvAddToWatchlistHeader.setOnClickListener {
                listener.onMyListClicked(this)
            }
            binding.cvPlayHeader.setOnClickListener {
                listener.onPlayMovieClicked(this)
            }
        }
    }

}