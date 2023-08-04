package com.muabdz.home.presentation.viewparam

import com.google.gson.annotations.SerializedName
import com.muabdz.shared.data.model.viewparam.MovieViewParam

data class HomeFeedsViewParam(
    @SerializedName("header")
    val header: MovieViewParam,
    @SerializedName("sections")
    val sections: List<SectionViewParam>
)
