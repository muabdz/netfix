package com.muabdz.netfix.router

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.muabdz.detail.presentation.ui.movieinfo.MovieInfoBottomSheet
import com.muabdz.shared.data.model.viewparam.MovieViewParam
import com.muabdz.shared.router.BottomSheetRouter

class BottomSheetRouterImpl: BottomSheetRouter {
    override fun createMovieInfoBottomSheet(movieViewParam: MovieViewParam): BottomSheetDialogFragment {
        return MovieInfoBottomSheet(movieViewParam)
    }
}