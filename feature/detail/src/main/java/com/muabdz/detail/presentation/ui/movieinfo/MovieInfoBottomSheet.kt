package com.muabdz.detail.presentation.ui.movieinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import coil.load
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.muabdz.detail.databinding.BottomSheetMovieInfoBinding
import com.muabdz.shared.data.model.viewparam.MovieViewParam
import com.muabdz.shared.utils.CommonUtils
import com.muabdz.shared.utils.ext.subscribe
import org.koin.android.ext.android.inject

class MovieInfoBottomSheet(private val movieViewParam: MovieViewParam): BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetMovieInfoBinding
    private val viewModel: InfoViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = BottomSheetMovieInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindMovie(movieViewParam)
        observeData()
    }

    private fun bindMovie(movie: MovieViewParam) {
        setClickListener(movie)
        loadPoster(movie.posterUrl)
        loadInfoMovie(movie)
    }

    private fun loadInfoMovie(movie: MovieViewParam) {
        binding.tvMovieTitle.text = movie.title
        binding.tvShortDesc.text = movie.overview
        binding.tvAdditionalInfo.text =
            "${movie.releaseDate} • ${movie.filmRate}"
        binding.ivWatchlist.setImageResource(CommonUtils.getWatchlistIcon(movie.isUserWatchlist))
    }

    private fun loadPoster(url: String) {
        binding.ivPoster.load(url)
    }

    private fun setClickListener(movie: MovieViewParam) {
        binding.ivClose.setOnClickListener {
            this.dismiss()
        }
        binding.llPlayMovie.setOnClickListener {
            // TODO: open player
        }
        binding.llShare.setOnClickListener {
            CommonUtils.shareFilm(requireContext(), movie)
        }
        binding.llMyList.setOnClickListener {
            viewModel.addOrRemoveWatchlist(movie)
        }
        binding.tvDetailMovie.setOnClickListener {
            // TODO: open detail movie
        }
    }

    private fun observeData() {
        viewModel.getWatchlistResult().observe(this) {
            it.subscribe(
                doOnSuccess = { result ->
                    result.payload?.let { movie ->
                        movie.isUserWatchlist = movie.isUserWatchlist
                        binding.ivWatchlist.setImageResource(CommonUtils.getWatchlistIcon(movie.isUserWatchlist))
                    }
                },
                doOnError = { error ->
                    Toast.makeText(requireContext(), error.message.orEmpty(), Toast.LENGTH_SHORT).show()
                }
            )
        }
    }
}