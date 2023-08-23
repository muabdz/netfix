package com.muabdz.shared.router

import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.muabdz.shared.data.model.viewparam.MovieViewParam

interface BottomSheetRouter {
    fun createMovieInfoBottomSheet(movieViewParam: MovieViewParam) : BottomSheetDialogFragment
}