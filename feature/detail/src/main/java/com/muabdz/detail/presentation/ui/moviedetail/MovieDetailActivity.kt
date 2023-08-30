package com.muabdz.detail.presentation.ui.moviedetail

import android.content.Context
import android.content.Intent
import androidx.core.view.isVisible
import coil.load
import com.muabdz.core.base.BaseActivity
import com.muabdz.detail.databinding.ActivityMovieDetailBinding
import com.muabdz.shared.data.model.viewparam.MovieViewParam
import com.muabdz.shared.router.ActivityRouter
import com.muabdz.shared.router.FragmentRouter
import com.muabdz.shared.utils.CommonUtils
import com.muabdz.shared.utils.ext.subscribe
import org.koin.android.ext.android.inject

class MovieDetailActivity : BaseActivity<ActivityMovieDetailBinding, DetailViewModel>(ActivityMovieDetailBinding::inflate) {
    override val viewModel: DetailViewModel by inject()

    private val activityRouter: ActivityRouter by inject()
    private val fragmentRouter: FragmentRouter by inject()

    private val movieId: String? by lazy { intent?.extras?.getString(EXTRA_MOVIE_ID) }

    companion object {
        private const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        fun createIntent(context: Context, movieId: String): Intent {
            return Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(EXTRA_MOVIE_ID, movieId)
            }
        }
    }

    override fun initView() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        enableHomeAsBack()

        movieId?.let {
            viewModel.fetchMovieDetail(it)
        }
    }

    override fun observeData() {
        super.observeData()
        viewModel.movieDetailResult.observe(this) {
            it.subscribe(
                doOnSuccess = {
                    showLoading(false)

                }, doOnLoading = {
                    showLoading(true)
                }, doOnError = {
                    showLoading(false)

                }
            )
        }
    }

    private fun showLoading(isShowLoading : Boolean) {
        binding.pbDetail.isVisible = isShowLoading
        binding.layoutDetail.layoutDetail.isVisible = !isShowLoading
    }

    private fun bindMovie(movie: MovieViewParam) {
        with(binding.layoutDetail) {
            layoutHeaderDetail.ivPosterDetail.load(movie.posterUrl)
            clDetailMovie.ivWatchlist.setImageResource(
                CommonUtils.getWatchlistIcon(movie.isUserWatchlist)
            )
            clDetailMovie.tvTitleMovie.text = movie.title
            clDetailMovie.tvMovieDesc.text = movie.overview
            clDetailMovie.tvAdditionalInfo.text = "${movie.releaseDate} • ${movie.filmRate} • ${movie.runtime}m"
            clDetailMovie.llShare.setOnClickListener {
                CommonUtils.shareFilm(this@MovieDetailActivity, movie)
            }
            clDetailMovie.llMyList.setOnClickListener {
                viewModel.addOrRemoveWatchlist(movie)
            }
            clDetailMovie.cvPlay.setOnClickListener {
                activityRouter.playerActivity(this@MovieDetailActivity, movie.videoUrl)
            }
            layoutHeaderDetail.ivPlayTrailer.setOnClickListener {
                binding.layoutDetail.layoutHeaderDetail.flHeaderPoster.isVisible = false
                binding.layoutDetail.layoutHeaderDetail.containerPlayer.isVisible = true
                supportFragmentManager.beginTransaction().apply {
                    replace(
                        binding.layoutDetail.layoutHeaderDetail.containerPlayer.id,
                        fragmentRouter.createPlayerFragment(movie.trailerUrl)
                    )
                }.commit()
            }
        }
    }

}